package pageObjects.sauceLab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.sauceLab.ProductPageUI;

public class ProductPageObject extends BasePage {
	WebDriver driver;

	public ProductPageObject(WebDriver driver) {
		this.driver = driver;

	}

	public void selectItemInProductSortDropdown(String textItem) {
		waitForElementClickable(driver, ProductPageUI.PRODUCT_CONTAINER_DROPDOWN);
		selectItemInDefaultDropdown(driver, ProductPageUI.PRODUCT_CONTAINER_DROPDOWN, textItem);

	}

	public boolean isProductNameByAscending() {
		// Khai báo ra 1 ArrayList để chứa product name trên UI
		ArrayList<String> productUIList = new ArrayList<String>();

		// Lấy ra hết tất cả các text product name
		List<WebElement> productNameText = getListWebElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);

		// Dùng vòng lặp để getText và add vào ArrayList trên(productUIList)
		for (WebElement productName : productNameText) {
			productUIList.add(productName.getText());
			System.out.println("Product Name ở trên UI: " + productName.getText());
		}

		// Tạo ra 1 ArrayList mới để sort dữ liệu trong cái ArrayList cũ xem đúng hay ko
		ArrayList<String> productSortList = new ArrayList<String>();
		for (String product : productUIList) {
			productSortList.add(product);
		}

		// Sort cái productSortList
		Collections.sort(productSortList);

		for (String productName : productSortList) {
			System.out.println("Product Name sau khi sort: " + productName);
		}
		// So sánh 2 List đã bằng nhau

		return productSortList.equals(productUIList);
	}

	public boolean isProductNameSortByAscendingLambda() {
		List<WebElement> elementLists = getListWebElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);
		List<String> names = elementLists.stream().map( n -> n.getText()).collect(Collectors.toList());
		List<String> sortedNames = new ArrayList<String>(names);
		Collections.sort(sortedNames);
		return names.equals(sortedNames);
		
	}
	public boolean isProductNameSortByDescendingLambda() {
		List<WebElement> elementLists = getListWebElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);
		List<String> names = elementLists.stream().map( n -> n.getText()).collect(Collectors.toList());
		List<String> sortedNames = new ArrayList<String>(names);
		Collections.sort(sortedNames);
		Collections.reverse(sortedNames);
		return names.equals(sortedNames);
		
	}
	
	public boolean isProductNameByDescending() {
		// Khai báo ra 1 ArrayList để chứa product name trên UI
		ArrayList<String> productUIList = new ArrayList<String>();

		// Lấy ra hết tất cả các text product name
		List<WebElement> productNameText = getListWebElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);

		// Dùng vòng lặp để getText và add vào ArrayList trên(productUIList)
		for (WebElement productName : productNameText) {
			productUIList.add(productName.getText());
			System.out.println("Product Name ở trên UI: " + productName.getText());
		}

		// Tạo ra 1 ArrayList mới để sort dữ liệu trong cái ArrayList cũ xem đúng hay ko
		ArrayList<String> productSortList = new ArrayList<String>();

		for (String product : productUIList) {
			productSortList.add(product);
		}

		// Sort cái productSortList
		Collections.sort(productSortList);

		for (String productName : productSortList) {
			System.out.println("Product Name sau khi sort ASC: " + productName);
		}

		// Reverse cái productSortList
		Collections.reverse(productSortList);
		for (String productName : productSortList) {
			System.out.println("Product Name sau khi sort DESC: " + productName);
		}

		// So sánh 2 List đã bằng nhau

		return productSortList.equals(productUIList);
	}

	public boolean isProductPriceSortByAscending() {
		// Khai báo ra 1 ArrayList để chứa product name trên UI
		ArrayList<Float> productUIList = new ArrayList<Float>();

		// Lấy ra hết tất cả các text product name
		List<WebElement> productPriceText = getListWebElement(driver, ProductPageUI.PRODUCT_PRICE_TEXT);

		// Dùng vòng lặp để getText và add vào ArrayList trên(productUIList)
		for (WebElement productPrice : productPriceText) {
			productUIList.add(Float.parseFloat(productPrice.getText().replace("$", "")));
			System.out.println("Product Price ở trên UI: " + productPrice.getText());

		}

		// Tạo ra 1 ArrayList mới để sort dữ liệu trong cái ArrayList cũ xem đúng hay ko
		ArrayList<Float> productSortList = new ArrayList<Float>();
		for (Float product : productUIList) {
			productSortList.add(product);
		}

		// Sort cái productSortList
		Collections.sort(productSortList);

		for (Float productName : productSortList) {
			System.out.println("Product Price sau khi sort: " + productName);
		}
		// So sánh 2 List đã bằng nhau

		return productSortList.equals(productUIList);
	}

	public boolean isProductPriceSortByDescending() {
		// Khai báo ra 1 ArrayList để chứa product name trên UI
		ArrayList<Float> productUIList = new ArrayList<Float>();

		// Lấy ra hết tất cả các text product price
		List<WebElement> productPriceText = getListWebElement(driver, ProductPageUI.PRODUCT_PRICE_TEXT);

		// Dùng vòng lặp để getText và add vào ArrayList trên(productUIList)
		for (WebElement productPrice : productPriceText) {
			productUIList.add(Float.parseFloat(productPrice.getText().replace("$", "")));
			System.out.println("Product Price ở trên UI: " + productPrice.getText());

		}

		// Tạo ra 1 ArrayList mới để sort dữ liệu trong cái ArrayList cũ xem đúng hay ko
		ArrayList<Float> productSortList = new ArrayList<Float>();
		for (Float product : productUIList) {
			productSortList.add(product);
		}

		// Sort cái productSortList
		Collections.sort(productSortList);

		for (Float productName : productSortList) {
			System.out.println("Product Price sau khi sort ASC: " + productName);
		}

		// Reverse cái productSortList
		Collections.reverse(productSortList);

		for (Float productName : productSortList) {
			System.out.println("Product Price sau khi sort DESC:" + productName);
		}

		// So sánh 2 List đã bằng nhau

		return productSortList.equals(productUIList);
	}

}
