require 'selenium-webdriver'
driver = Selenium::WebDriver.for :chrome

Given(/^We navigate to our page$/) do
  driver.navigate.to "http://127.0.0.1:9000/hello"
end

When(/^We enter our name in the edit box and clicking the button$/) do
  driver.find_element(:id, 'inputNameBox').send_keys("Ivan Grozny")
  driver.find_element(:id, 'submitButton').click
end

Then(/^The results of name entered will be displayed$/) do
  wait = Selenium::WebDriver::Wait.new(:timeout => 10) # seconds
  begin
    element = wait.until { driver.find_element(:id => "resultText") }
    expect(element.text).to eq('Welcome to the system, Ivan Grozny')
  ensure
    driver.quit
  end
end