/**
 * This is sample script for seleniumJS
 * 
 * 
 */
 
driver.get("http://www.google.com");
 
helper.waitLoad();

driver.findElement(By.id("lst-ib")).clear();
driver.findElement(By.id("lst-ib")).sendKeys("yni3");

var classes = driver.findElements(By.className("jsb"));
for (var i = 0; i < classes.size(); i++) {
    var element = classes.get(i);
    if (element.findElements(By.cssSelector("input[name*=btnK]")).size()) {
        element.findElement(By.cssSelector("input[name*=btnK]")).click();
		break;
    }
}



