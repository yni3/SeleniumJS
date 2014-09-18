package org.yni3.selenium;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

/**
 *
 * @author yni3
 */
class ByObject extends By {

    ByObject() {
    }

    @Override
    public List<WebElement> findElements(SearchContext sc) {
        return null; //何もしない
    }
}
