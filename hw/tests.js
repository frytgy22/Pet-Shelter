
QUnit.test( "Задание 1, Напишите функцию isUpperCaseFirstLetter", function( assert ) {
	assert.equal(isUpperCaseFirstLetter('lorem'), false);
	assert.equal(isUpperCaseFirstLetter('Lorem'), true);
	assert.equal(isUpperCaseFirstLetter('лорем'), false);
	assert.equal(isUpperCaseFirstLetter('Лорем'), true);
});

QUnit.test( "Задание 2. Напишите функцию isValidEmail", function( assert ) {
	assert.equal(isValidEmail('anna'), false);	
	assert.equal(isValidEmail('anna@'), false);	
	assert.equal(isValidEmail('@mail.com'), false);	
	assert.equal(isValidEmail('anna@mail.com'), true);	
	assert.equal(isValidEmail('anna.mail@mail.com'), true);	
	assert.equal(isValidEmail('anna01.mail@mail.ua'), true);	
	assert.equal(isValidEmail('anna.mail@mail.'), false);	
	assert.equal(isValidEmail('anna.mail@.mail.com'), false);	
});

QUnit.test( "Задание 3. Напишите функцию trimString", function( assert ) {
	const testString = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
	assert.equal(trimString("\t"+testString), testString);	
	assert.equal(trimString(testString + "\t"), testString);
	assert.equal(trimString("\t"+testString + "\t"), testString);	
	assert.equal(trimString("\n"+testString), testString);	
	assert.equal(trimString(testString + "\n"), testString);
	assert.equal(trimString("\n"+testString + "\n"), testString);	
	assert.equal(trimString("   "+testString), testString);			
	assert.equal(trimString(testString + "    "), testString);	
	assert.equal(trimString("    "+testString + "    "), testString);	
	assert.equal(trimString("\n \t"+testString + "\n  \t"), testString);	
});

QUnit.test( "Задание 4.  Напишите функцию thousandsSeparators", function( assert ) {
	assert.equal(thousandsSeparators(1000), "1,000");
	assert.equal(thousandsSeparators(10000.23), "10,000.23");
	assert.equal(thousandsSeparators(100000), "100,000");
});

