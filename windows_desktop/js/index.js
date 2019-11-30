window.addEventListener('load', () => {

    //перетаскивание, удаление в корзину файла
    var mouse = {
        moveX: 0,
        moveY: 0,
        mouseDown: false,
    };

    document.querySelector('main').addEventListener('drop', function (ev) {
        mouse.moveY = ev.pageY;
        mouse.moveX = ev.pageX;
        ev.preventDefault();
        mouse.mouseDown = false;
    });
    document.querySelector('main').addEventListener('dragover', function (ev) {
        ev.preventDefault();
    });
    document.addEventListener('keydown', (e) => {
        if (e.key === 'Delete' && mouse.mouseDown) {
            trash.img.src = trash.icons['full'];
            file.delete();
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
            img.style.zIndex = '100';
            img.addEventListener('dragover', function (ev) {
                ev.preventDefault();
            });
            img.addEventListener('drop', function (ev) {
                ev.preventDefault();
                file.delete();
                img.src = trash.icons['full'];
            });
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
        icon: "images/3.png",

        init: function () {
            let img = createImg('file image', 64, 10, 10);
            img.src = this.icon;
            img.draggable = true;

            img.addEventListener('mousedown', this.mouseDown.bind(this));
            img.addEventListener('mouseup', function () {
                mouse.mouseDown = false;
            });
            img.addEventListener('mouseout', function () {
                mouse.mouseDown = false;
            });
            img.addEventListener('dragstart', function (e) {
                e.dataTransfer.setData('text/plain', 'This text may be dragged');
            });
            img.addEventListener('dragend', this.mouseEnd.bind(this),);
            this.img = img;
            this.draw();
        },

        mouseDown: function () {
            mouse.mouseDown = true;
        },
        mouseEnd: function (e) {
            this.img.style.top = mouse.moveY - this.img.offsetHeight / 2 + 'px';
            this.img.style.left = mouse.moveX - this.img.offsetWidth / 2 + 'px';
            document.getElementById('fileName').style.top = mouse.moveY - this.img.offsetHeight / 2 + 'px';
            document.getElementById('fileName').style.left = mouse.moveX - this.img.offsetWidth / 2 + 'px';
        },
        draw: function () {
            document.body.appendChild(this.img);
        },
        remove: false,

        delete: function () {
            this.img.style.display = 'none';
            this.remove = true;
            document.getElementById('fileName').style.display = 'none';
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

    //меню корзины
    let ul = document.getElementById('ul');
    let name = document.getElementById('fileName');
    let child = ul.childNodes;
    for (let i = 0; i < child.length; i++) {
        child[i].addEventListener('click', function () {
            let answer = confirm(child[i].firstChild.textContent + "?");
            ul.style.display = 'none';
            if (answer && file.remove) {
                trash.img.src = trash.icons.empty;
                if (i === 1) {
                    file.img.style.display = 'block';
                    if (name !== null) {
                        name.style.display = 'block';
                    }
                    file.remove = false;
                } else {
                    file.img.remove();
                    name.remove();
                }
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