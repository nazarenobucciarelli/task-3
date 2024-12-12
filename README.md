## Waiters



The default page loading strategy in Selenium is ‘complete’. It means that selenium will start to interact after the HTML is loaded. The problem is that sometimes with JS we do dynamic interactions with elements, or the js takes more time in load a specific element. To solve this, we use waiters. In many cases, people use sleep to wait for something, that is a very bad approach.



Explicit waiters: Selenium has a built in implicit waiter, which value is 0 in seconds. It means that if the element is not found when the dom is loaded, then it will throw an error. We can also use explicit waiters, specifying the number of seconds, and it doesnt mean that it will wait the amount we specified, since if the element shows up before, it will stop waiting. 





Implicit waiters: Are loops added to the code that poll the application for a specific condition to evaluate as true before it exits the loop and continues to the next command in the code. If the condition is not met before a designated timeout value, the code will give a timeout error. 