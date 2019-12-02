window.addEventListener('load', () => {
    var mouse = {
        mouseDown: false,
        inTrash: false,
    };
    document.addEventListener('keydown', (e) => {
        if (e.key === 'Delete' && mouse.mouseDown) {
            file.delete();
            mouse.inTrash = false;
            mouse.mouseDown = false;
        }
    });

    const trash = {
        icons: {
            'empty': 'images/1.jpg',
            'full': 'images/2.png'
        },
        init: function () {
            let img = createImg('trash image', 64, 480, 700);
            img.src = this.icons['empty'];
            img.setAttribute('class', 'trash');
            img.ondragstart = function () {
                return false;
            };
            img.addEventListener('contextmenu', function (e) {
                e.preventDefault();
                document.getElementById('ul').style.display = 'block';
            });
            this.img = img;
            this.draw();
        },
        draw: function () {
            document.body.appendChild(this.img);
        }
    };

    const file = {
        icon: 'images/3.png',

        init: function () {
            let img = createImg('file image', 64, 10, 10);
            img.src = this.icon;
            img.ondragstart = function () {
                return false;
            };
            img.addEventListener('mousedown', this.mousedown.bind(this));
            this.img = img;
            this.draw();

        },

        mousedown: function (event) {
            if (event.which === 1) {//если клик левый (DEPRECATED, не нашла, чем заменить)
                let img = this.img;
                mouse.mouseDown = true;
                let shiftX = event.clientX - img.getBoundingClientRect().left;
                let shiftY = event.clientY - img.getBoundingClientRect().top;

                function moveAt(pageX, pageY) {
                    img.style.left = pageX - shiftX + 'px';
                    img.style.top = pageY - shiftY + 'px';
                }

                moveAt(event.pageX, event.pageY);
                let currentDrop = null;

                function onMouseMove(event) {
                    if (document.body.clientWidth < (event.pageX + img.offsetWidth - (shiftX)) ||
                        document.body.clientHeight < (event.pageY + img.offsetHeight +
                            document.querySelector('footer').offsetHeight + 10 - (shiftY)) ||
                        event.pageX <= 1 || event.pageY <= 1) {
                        document.body.removeEventListener('mousemove', onMouseMove);
                        return;
                    }

                    moveAt(event.pageX, event.pageY);
                    img.hidden = true;
                    let elemBelow = document.elementFromPoint(event.clientX, event.clientY);//ищем объект под файлом
                    img.hidden = false;
                    if (!elemBelow) return;
                    let dropBelow = elemBelow.closest('.trash');
                    if (currentDrop !== dropBelow) {
                        //   currentDrop=null,если мы были не над trash до этого события (например, над пустым пространством)
                        //   dropBelow=null,если мы не над trash именно сейчас, во время этого события
                        if (currentDrop) {
                            // логика обработки процесса "вылета"
                            mouse.inTrash = false;
                        }
                        currentDrop = dropBelow;
                        if (currentDrop) {
                            // логика обработки процесса, когда мы "влетаем" в элемент trash
                            mouse.inTrash = true;
                        }
                    }
                }

                document.body.addEventListener('mousemove', onMouseMove);
                img.onmouseup = function () {
                    mouse.mouseDown = false;
                    if (mouse.inTrash) {
                        file.delete();
                        mouse.inTrash = false;
                    }
                    document.body.removeEventListener('mousemove', onMouseMove);
                    img.onmouseup = null;
                };
            }
        },
        remove: false,
        draw: function () {
            document.body.appendChild(this.img);
        },
        delete: function () {
            this.remove = true;
            trash.img.src = trash.icons.full;
            this.img.style.display = 'none';
        }
    };

    trash.init();
    file.init();

    function createImg(alt, size, top, left) {
        let img = document.createElement('img');
        img.alt = alt;
        img.style.position = 'absolute';
        img.style.width = `${size}px`;
        img.style.top = `${top}px`;
        img.style.left = `${left}px`;
        return img;
    }

    ////////////////////////////////////////////////////////////////////

    //меню пуск
    let win = document.getElementById('win');
    win.addEventListener('mousedown', function () {
        win.style.transition = 'all 0.1s linear';
        win.style.transform = 'scale(1.3)';

    });
    win.addEventListener('mouseup', function () {
        win.style.transition = 'all 0.4s linear';
        win.style.transform = 'scale(1)';
    });

    win.addEventListener('click', function () {
        document.getElementById('menu').style.display = 'block';
    });


    /////////////////////////////////////////////////////////////////

    //меню корзины (правый клик)
    let ul = document.getElementById('ul');
    let child = ul.childNodes;
    for (let i = 0; i < child.length; i++) {
        child[i].addEventListener('click', function () {
            let answer = confirm(child[i].firstChild.textContent + "?");
            ul.style.display = 'none';
            if (answer && file.remove) {
                trash.img.src = trash.icons.empty;
            }
        });
    }
    document.body.addEventListener('mousedown', function (e) {
        let type = e.target;
        if (type.tagName !== 'LI' && type.tagName !== 'A') {
            document.getElementById('ul').style.display = 'none';
            document.getElementById('menu').style.display = 'none';
        }
    });
});