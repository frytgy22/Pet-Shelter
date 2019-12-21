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
        let button = document.createElement("button"); //added buttons for "li"
        button.appendChild(document.createTextNode("КУПИТЬ"));
        button.classList.add("button25");
        button.classList.add("threed");
        button.name = number;
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
    let buttons = document.getElementsByTagName("button");
    for (let i = 0; i < buttons.length; i++) {
        buttons[i].addEventListener("click", function () {
            console.log(buttons[i].name)
        })
    }
///////////////////////////////////////////////////////////////////////////////////////
document.getElementById("basket").addEventListener("mouseover",function () {
this.classList.add("mouseover");
this.style.background="red";
})
});