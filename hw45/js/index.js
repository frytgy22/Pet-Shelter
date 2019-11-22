;(function () {
    'use strict';

    var Gallery = function (id, setup) {
        // настройки по-умолчанию
        this.defaults = {
            margin: 10,		// расстояние между элементами [px]
            visibleItems: 1,		// сколько элементов показывать одновременно
            autoScroll: false,	// автоматическое прокручивание
            interval: 3000,	// задержка при автоматическом прокручивании [ms]
            nav: true,	// показать/скрыть кнопки next/prev
            baseTransition: 1	// скорость анимации
        };

        this.id = id;
        this.setup = setup;

        // основные DOM-элементы галереи определяющие её каркас
        // родительский элемент галереи
        this.gallery = document.getElementById(this.id);
        // контейнер в котором отображаются элементы галереи
        this.slider = this.gallery.querySelector('.slider');
        // контейнер, непосредственно в котором расположены элементы слайдера
        this.stage = this.gallery.querySelector('.stage');
        // элементы слайдера
        this.items = this.gallery.querySelectorAll('.stage > div');
        // количество элементов в слайдере
        this.count = this.items.length;

        this.current = 0;		// index координаты текущего элемента
        this.next = 0;			// index координаты следующего элемента

        // построение галереи исходя из полученных настроек
        this.init();
    };

    // запишем конструктор в свойство 'window.Gallery', чтобы обеспечить
    // доступ к нему снаружи анонимной функции
    window.Gallery = Gallery;
    // для сокращения записи, создадим переменную, которая будет ссылаться
    // на прототип 'Gallery'
    var fn = Gallery.prototype;


    fn.init = function () {
        this.options = extend({}, this.defaults, this.setup);
        // формируем каркас галереи
        this.setSizeCarousel();
        // заполняем массив с координатами X каждого элемента слайдера
        this.setCoordinates();
        // формируем управление слайдером в зависимости от настроек
        this.initControl();
        // устанавливаем обработчики событий, если ещё не устанавливались
        if (!this.events) {
            this.registerEvents();
        }
    };

    //формируем каркас галереи
    fn.setSizeCarousel = function () {
        // получаем ширину слайдера - вьюпорт, в котором прокручиваются элементы галереи
        this.widthSlider = this.slider.offsetWidth;

        // максимальный индекс, который может быть у текущего элемента
        // чтобы на последней странице галереи при использовании пагинатора
        // наблюдалось кол-во элементов равное options.visibleItems
        this.max = this.count - this.options.visibleItems;

        // получаем ширину элемента слайдера, которая зависит от колличества
        // одновременно видимых элементов, размера отступа между элементами и
        // общей ширины слайдера
        // от ширины слайдера вычитаем сумму отступов
        // уменьшенную на 1, т.к. отступ последнего видимого элемента не попадает
        // в окно слайдера (контейнер '.stage')
        var width = (this.widthSlider - this.options.margin * (this.options.visibleItems - 1)) / this.options.visibleItems;

        // значение, по которому отсчитываются координаты
        // состоит из ширины элемента слайдера и его margin-right
        // другими словами - растоянием между левыми границами элементов слайдера
        this.width = width + this.options.margin;
        // ширина контейнера '.stage', непосредственно в котором
        // расположены элементы слайдера
        this.widths = this.width * this.count;
        // задаётся стиль ширины контейнера '.stage'
        this.stage.style.width = this.widths + 'px';
        // перебираем коллекцию элементов слайдера и
        // прописываем ширину и правый отступ для каждого элемента
        [].forEach.call(this.items, function (el) {
            el.style.cssText = 'width:' + width + 'px; margin-right:' + this.options.margin + 'px;';
        }.bind(this));

        // после того, как каркас галереи построен, все размеры элементов
        // вычислены и прописаны в стилях, делаем карусель видимой через
        // свойство стиля 'visibility'
        this.gallery.style.visibility = 'visible';
    };

    // заполняем массив с коодинатами X каждого элемента слайдера
    fn.setCoordinates = function () {
        // координата первого элемента, от неё и будет идти отсчёт
        var point = 0;
        // добавляем новое свойство в объект 'options'
        // пока это пустой массив
        this.coordinates = [];

        // заполняем в цикле массив пока количество его элементов
        // не станет равно количеству элементов слайдера,
        // т.е. будет записана координата X каждого элемента
        while (this.coordinates.length < this.count) {
            // добавляем в конец массива текущее значение переменной 'point'
            // которое равно координате X текущего элемента слайдера
            this.coordinates.push(point);
            // вычитаем из текущей координаты ширину блока, равную
            // сумме ширины элемента слайдера и отступа или другими
            // словами - расстояние между левыми границами элементов
            point -= this.width;
        }
        return null;
    };

    // формирования навигации галереи
    fn.initControl = function () {
        // объект с кнопками навигации 'prev / next'
        this.navCtrl = this.gallery.querySelector('.nav-ctrl');

        if (this.options.nav === true) {
            // кнопка 'prev'
            this.btnPrev = this.navCtrl.querySelector('[data-shift=prev]');
            // кнопка 'next'
            this.btnNext = this.navCtrl.querySelector('[data-shift=next]');
            // устанавливаем стили для кнопок 'prev' и 'next'
            this.setNavStyle();
            // делаем навигацию видимой
            this.navCtrl.style.display = 'block';
        } else {
            // делаем навигацию невидимой
            this.navCtrl.removeAttribute('style');
        }
    };


    fn.setNavStyle = function () {
        // убираем у всех кнопок класс 'disable', теперь
        // обе кнопки выглядят активными
        this.btnPrev.classList.remove('disable');
        this.btnNext.classList.remove('disable');

        if (this.current === 0) {
            // если первый элемент является текущим, то блокируем попытку просмотра
            // предыдущего элемента, т.к. его не существует и делаем кнопку
            // 'prev' неактивной
            this.btnPrev.classList.add('disable');
        } else if (this.current >= this.count - this.options.visibleItems) {
            // если последний элемент появился на экране, при этом не важен
            // его индекс, блокируем и делаем неактивной кнопку просмотра след.
            // элемента на экране будет наблюдаться столько элементов,
            // сколько указано в visibleItems
            this.btnNext.classList.add('disable');
        }
        return null;
    };


    /////////////////////////////////////////
    // обработка событий управления галереей

    // регистрация обработчиков событий
    fn.registerEvents = function () {
        // регистрируем обработчик изменения размеров окна браузера

        if (this.options.autoScroll) {
            setInterval(this.autoScroll.bind(this), this.options.interval);
        }
        // управление кликом по кнопкам 'prev / next' объекта 'navCtrl'
        this.navCtrl.addEventListener('click', this.navControl.bind(this));

        // флаг, информирующий о том, что обработчики событий установлены
        this.events = true;
    };

    // автоматическое прокручивание галереи
    fn.autoScroll = function (e) {
        // получаем координату Х элемента, до которого должен переместиться слайдер
        // галерея всегда прокручивается вправо, поэтому аргумент, через который
        // передаётся direction, всегда равен 1
        var x = this.getNextCoordinates.call(this, 1);
        // запускаем прокручивание галереи
        this.scroll.apply(this, [x, this.options.baseTransition]);

        return null;
    };

    // управление галерей кнопками 'prev / next'
    fn.navControl = function (e) {
        // если клик был сделан не по элементу 'span' объекта
        // navCtrl, прекращаем работу функции
        if (e.target.tagName !== 'SPAN') return;
        // определяем направление прокручивания галереи
        // зависит от кнопки, по которой был сделан клик
        // -1 - prev, 1 - next
        var direction = (e.target.getAttribute('data-shift') === 'next') ? 1 : -1,
            // получаем координату Х элемента, до которого должен переместиться слайдер
            x = this.getNextCoordinates(direction);
        // запускаем прокручивание галереи
        this.scroll(x, this.options.baseTransition);
    };


    /////////////////////////////////////////
    // прокручивание галереи

    // получаем новую координату, до которой должна проскроллиться галерея
    fn.getNextCoordinates = function (direction) {
        if (typeof (direction) !== 'number') return this.coordinates[this.current];

        // direction - направление перемещения: -1 - left, 1 - right
        if (this.options.autoScroll && this.current >= this.count - this.options.visibleItems) {
            this.next = 0;
        } else {
            // попытка прокрутить к предыдущему элементу, когда текущим является первый элемент
            if (this.current === 0 && direction === -1 ||
                // попытка просмотреть следующую группу элементов при постраничной навигации, но
                // все элементы после текущего выведены во вьюпорт слайдера
                (this.current >= this.max) && direction === 1) {
                return;
            }
            // получаем индекс следующего элемента
            this.next += direction;
        }
        // возвращаем координату след. элемента - координату, до которой
        // необходимо продвинуть галерею
        return this.coordinates[this.next];
    };

    // скроллинг галереи
    fn.scroll = function (x, transition) {
        // если аргумент х не является числом, прекращаем работу функции
        if (typeof (x) !== 'number') return;
        // прописываем новые стили для смещения (прокручивания) галереи
        // к следующему элементу

        this.stage.style.cssText = 'width:' + this.widths + 'px; ' +
            'height:' + this.items[0].offsetHeight + 'px; ' +
            'transform:translateX(' + x + 'px); ' +
            'transition:' + transition + 's';
        // после прокручивания, индекс след. элемента становится текущим
        this.current = (this.next < this.max) ? this.next : this.max;
        // делаем анимацию для текушей картинки, для остальныйх сброс анимации
        var anim = document.getElementsByTagName("img");
        for (var i = 0; i < anim.length; i++) {
            if (i === this.current) {
                anim[i].setAttribute("class", "anim");
            } else {
                anim[i].setAttribute("class", "noAnim");
            }
        }

        // меняем стили отображения кнопок управления в зависимости от
        // текущего индекса
        if (this.options.nav) {
            this.setNavStyle();
        }
        return null;
    };

    // объединяет и перезаписывает значения двух объектов
    // и выдаёт общий результат
    function extend(out) {
        out = out || {};
        for (var i = 1; i < arguments.length; i++) {
            if (!arguments[i])
                continue;
            for (var key in arguments[i]) {
                if (arguments[i].hasOwnProperty(key))
                    out[key] = arguments[i][key];
            }
        }

        return out;
    }


    //начать/остановить слайд-шоу
    var idShow;
    fn.slideShow = function (time) {
        this.options.autoScroll = true;
        idShow = setInterval(this.autoScroll.bind(this), time);
    };

    fn.stopShow = function () {
        clearInterval(idShow);
    }
})();