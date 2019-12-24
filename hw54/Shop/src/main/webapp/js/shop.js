window.addEventListener('load', () => {

    let li = document.getElementsByTagName("li");

    function createImg(number) {
        let img = document.createElement("img"); //added img for "li"
        img.src = "./images/sushi/" + number + ".jpeg";
        img.alt = "sushi";
        img.classList.add("imgs");
        return img;
    }

    for (let i = 0; i < li.length; i++) {
        let img = createImg(i + 1);
        li[i].prepend(img);
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////

    function createItem(number) {
        let button = document.createElement("button");     //added buttons for "li"
        button.appendChild(document.createTextNode("КУПИТЬ"));
        button.classList.add("button25", "button1");
        button.id = number;
        return button;
    }

    for (let i = 0; i < li.length; i++) {
        let button = createItem(i + 1);
        li[i].appendChild(button);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////

    for (let i = 0; i < li.length; i++) {
        li[i].addEventListener("mouseover", function () { //увеличиваю img
            let img = this.firstChild;
            img.style.transform = "scale(1.05)";
            img.style.transition = "all 0.5s linear";
            this.addEventListener("mouseout", function () {
                img.style.transform = "scale(1)";
                img.style.transition = "all 0.5s linear";
            })
        })
    }
////////////////////////////////////////////////////////////////////////////////////////

    var buttons = document.getElementsByClassName("button1"); //listeners for buttons
    var arrayOrder = [];//for id goods
    let goods = document.getElementById("goods");

    for (let i = 0; i < buttons.length; i++) {
        buttons[i].addEventListener("click", buttonReplaceValue);
        buttons[i].addEventListener("click", countGoodsInBasket);
        buttons[i].addEventListener("click", function () {
            arrayOrder.push(this.id);
            goods.appendChild(createLi(this.name, i + 1)); // added goods in list for customer
        })
    }

    function createLi(text, number) {
        let li = document.createElement("li");
        let img = document.createElement("img");
        img.src = "./images/sushi/" + number + ".jpeg";
        img.alt = 'sushi';
        li.appendChild(img);
        let span = document.createElement("span");
        span.appendChild(document.createTextNode('❌'));
        li.appendChild(span);
        let p = document.createElement("p");
        p.appendChild(document.createTextNode(text));
        li.appendChild(p);
        return li;
    }

    function buttonReplaceValue() {//при добавлении в корзину уменьшаю кол-во товара
        console.log(this.id);//id goods
        console.log(this.value);//count goods
        console.log(this.name);//name goods
        if (!this.disabled) {
            this.value = (parseInt(this.value) - 1).toString(); //если кол-во = 0, кнопка не активна
            if (this.value < 1) {
                this.disabled = true;
                this.style.background = "linear-gradient(#a4a4a4, #a4a4a4 48%, #848484 52%, #3c3c3c)";
                this.style.boxShadow = "none";
                this.style.border = " 2px solid #848484";
                this.replaceChild(document.createTextNode("ПРОДАНО"), this.firstChild);
                this.style.paddingLeft = '3px';
            }
        }
    }

    var basket = document.getElementById("count");

    function countGoodsInBasket() {     //кол-во товаров в корзине
        let child = basket.firstChild;
        basket.replaceChild(document.createTextNode((parseInt(child.textContent) + 1).toString()), child);
    }

///////////////////////////////////////////////////////////////////////////////////////

    let form = document.querySelector("form");
    var i = 1;//name input устанавливаю по порядку от 1(для считывания getParam

    function createInput(id) {
        let label = document.createElement("label");    //added input in form
        let input = document.createElement("input");
        input.value = id;
        input.name = i.toString();
        i++;
        label.appendChild(input);
        return label;
    }

    document.querySelector("form").addEventListener("submit", function (e) {
        e.preventDefault();
        setTimeout(createForm, 2000);//при клике на "отпрваить заказ" записываю все id заказов в форму
    });//timeout 2 sec, что прошла вся запись в form


    function createForm() {
        for (let i = 0; i < arrayOrder.length; i++) {
            form.appendChild(createInput(arrayOrder[i])); // added goods.id in form
        }
        form.submit();
    }

    document.querySelector("section").addEventListener("click", seeBasket);//показать корзину;

    function seeBasket() {
        if (arrayOrder.length > 0) {
            document.getElementById("order").style.display = "block";
            $("#order").animate({top: "+=2100"}, 1500);

            function addClass() {
                $("#order").addClass('transform');
            }

            setTimeout(addClass, 1900);
            $("main").animate({opacity: 0,}, 2000);
            document.querySelector("section").removeEventListener("click", seeBasket);
        } else {
            alert("Корзина пуста!");
        }
    }

    document.getElementById("close").addEventListener("click", function () {
        document.getElementById("order").style.display = "none";
        $("#order").animate({top: "-=2100"}, 1500);
        $("main").animate({opacity: 1,}, 2000);
        document.querySelector("section").addEventListener("click", seeBasket);
    });

//////////////////////////////////////////////////////////////////////////////////////////

    let selected;      //remove order
    document.getElementById("order").addEventListener("click", function () {
        let target = event.target;
        if (target.tagName === 'SPAN') {
            removeTask(target);
        }
    });

    function removeTask(span) {
        selected = span;
        let text = selected.parentElement.textContent.toString(); //увеличиваю кол-во товара
        let but = document.getElementsByName(text.substring(1))[0];
        if (but.value < 1) {
            but.disabled = false;
            but.style.background = " linear-gradient(#FB9575, #F45A38 48%, #EA1502 52%, #F02F17)";
            but.style.boxShadow = "0 0 0 60px rgba(0, 0, 0, 0) inset, .1em .1em .2em #800";
            but.style.border = "2px solid #F64C2B";
            but.replaceChild(document.createTextNode("КУПИТЬ"), but.firstChild);
            but.style.paddingLeft = '7px';
        }
        but.value = (parseInt(but.value) + 1).toString();
        $(selected.parentElement).hide(1000);
        for (let i = 0; i < arrayOrder.length; i++) {
            if (arrayOrder[i] == but.id) {
                arrayOrder.splice(i, 1);
                break;
            }
        }
        basket.replaceChild(document.createTextNode((parseInt(basket.firstChild.textContent) - 1).toString()), basket.firstChild);
        console.log(arrayOrder);
    }
});