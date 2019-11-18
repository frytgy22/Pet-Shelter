// 1. Напишите функцию isUpperCaseFirstLetter, которая проверяет входную строку на наличие
// первой прописной (большой буквы).
// Возвращает true - если первая буква в слове прописна, иначе - false.

function isUpperCaseFirstLetter(str) {
    if (str == null) return str;
    let reg = /[A-ZА-Я]/;
    return reg.test(str.charAt(0));
}

//2. Напишите функцию isValidEmail, которая проверяет является ли входная строка валидным email адресом.
// Возвращает true - если email валидный, иначе - false.

function isValidEmail(str) {
    if (str == null) return str;
    let reg = /\w+@(\w+\.)+\w+/;
    return reg.test(str);
}

// 3. Напишите функцию trimString, которая работает по аналогии со строковым методом trim,
//     т.е. удаляет начальные и конечные пробелы, а также удаляет повторяющиеся пробелы внутри строки.
//     Возвращает измененную строку.

function trimString(str) {
    if (str == null) return str;
    return str.replace(/^\s+|\s+$/g, '');
}

// 4. Напишите функцию thousandsSeparators для отделения тысяч при печати целой части числа.
//     Функция принимает число (number), а возвращает строку.

function thousandsSeparators(num) {
    if (num == null) return num;
    return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

