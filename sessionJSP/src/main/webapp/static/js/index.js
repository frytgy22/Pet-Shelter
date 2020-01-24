window.addEventListener('load', () => {
    document.getElementById("table").addEventListener('click', function (event) {
        let target = event.target;

        if (target.tagName === 'BUTTON') {

            if (target.textContent === 'edit') {

                let text = document.createTextNode('Edit CD: ' + target.parentElement.previousElementSibling.previousElementSibling.textContent);
                let info = document.getElementById("info");

                if (info.firstChild !== null) {
                    info.replaceChild(text, info.firstChild);
                } else {
                    info.appendChild(text);
                }
                let form = document.getElementById('edit');

                form.firstElementChild.nextElementSibling.value =
                    target.parentElement.parentElement.firstElementChild.textContent;//id

                form.firstElementChild.nextElementSibling.nextElementSibling.firstElementChild.value =
                    target.parentElement.previousElementSibling.previousElementSibling.textContent;//name

                form.lastElementChild.previousElementSibling.previousElementSibling.firstElementChild.value =
                    target.parentElement.previousElementSibling.textContent;//singer

                animate('button', 0, 'none');
                animate('table', 0, 'none');
                animate('edit', 1, 'block');

            } else if (target.textContent === 'delete') {

                let form = document.getElementById('delete');
                form.firstElementChild.value = target.parentElement.parentElement.firstElementChild.textContent;//id
                form.submit();
            }
        }
    });

    function animate(id, opacity, display) {
        let el = document.getElementById(id);
        $(el).animate({opacity: opacity,}, 1000);
        el.style.display = display;
    }
});