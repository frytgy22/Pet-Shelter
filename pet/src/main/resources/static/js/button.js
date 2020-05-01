window.addEventListener('load', () => {

    let elementsByClassName = document.getElementsByClassName("delete");
    if (elementsByClassName != null) {

        for (let i = 0; i < elementsByClassName.length; i++) {
            elementsByClassName[i].addEventListener('click', function (event) {
                if (confirm("The category can be used in posts!" +
                    " Are you sure you want to delete the category with posts?")) {

                    $(location).attr('href', this.href);
                }
            });
        }
    }
});