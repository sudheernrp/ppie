This repo used to how to develop a CRUD RESTFul API using Spring Boot, Spring Data JPA, Maven, and embedded H2 database.

<b>Note:</b> We configure the H2 database with Spring boot to create and use an in-memory database in runtime, generally for unit testing or POC purposes. Remember an in-memory database is created/initialized when an application starts up; and destroyed when the application shuts down.

<b>1. What we'll build</b>
We will build a <b>CRUD RESTFul APIs</b> for a Simple <font color="#d73a49">Employee Management System</font> using Spring Boot 2 JPA and H2 database. Following are five REST APIs (Controller handler methods) are created for <font color="#d73a49">Employee resource</font>.

<img src="https://4.bp.blogspot.com/-GIlhRU_3AdQ/W5trMB5AyII/AAAAAAAADyE/b8LXiR5fiFYC2Z9GdYiOYjV6w5gZv9jaQCLcBGAs/s1600/rest-api-list.PNG"></img>

<b>Exception(Error) Handling for RESTful Services</b>
Spring Boot provides a good default implementation for exception handling for RESTful Services. Let’s quickly look at the default Exception Handling features provided by Spring Boot.

Heres what happens when you fire a request to not resource found: <a href="http://localhost:8080/some-dummy-url">http://localhost:8080/some-dummy-url</a>

<div class="highlight highlight-source-json" style="box-sizing: border-box; color: #24292e; margin-bottom: 16px; overflow: visible !important;">
<pre style="background-color: #f6f8fa; border-radius: 3px; border: 1px solid rgb(61, 133, 198); box-sizing: border-box; font-family: SFMono-Regular, Consolas, &quot;Liberation Mono&quot;, Menlo, Courier, monospace; font-size: 13.6px; line-height: 1.45; overflow-wrap: normal; overflow: auto; padding: 16px; word-break: normal;">{
  <span class="pl-s" style="box-sizing: border-box; color: #032f62;"><span class="pl-pds" style="box-sizing: border-box;">"</span>timestamp<span class="pl-pds" style="box-sizing: border-box;">"</span></span>: <span class="pl-c1" style="box-sizing: border-box; color: #005cc5;">1512713804164</span>,
  <span class="pl-s" style="box-sizing: border-box; color: #032f62;"><span class="pl-pds" style="box-sizing: border-box;">"</span>status<span class="pl-pds" style="box-sizing: border-box;">"</span></span>: <span class="pl-c1" style="box-sizing: border-box; color: #005cc5;">404</span>,
  <span class="pl-s" style="box-sizing: border-box; color: #032f62;"><span class="pl-pds" style="box-sizing: border-box;">"</span>error<span class="pl-pds" style="box-sizing: border-box;">"</span></span>: <span class="pl-s" style="box-sizing: border-box; color: #032f62;"><span class="pl-pds" style="box-sizing: border-box;">"</span>Not Found<span class="pl-pds" style="box-sizing: border-box;">"</span></span>,
  <span class="pl-s" style="box-sizing: border-box; color: #032f62;"><span class="pl-pds" style="box-sizing: border-box;">"</span>message<span class="pl-pds" style="box-sizing: border-box;">"</span></span>: <span class="pl-s" style="box-sizing: border-box; color: #032f62;"><span class="pl-pds" style="box-sizing: border-box;">"</span>No message available<span class="pl-pds" style="box-sizing: border-box;">"</span></span>,
  <span class="pl-s" style="box-sizing: border-box; color: #032f62;"><span class="pl-pds" style="box-sizing: border-box;">"</span>path<span class="pl-pds" style="box-sizing: border-box;">"</span></span>: <span class="pl-s" style="box-sizing: border-box; color: #032f62;"><span class="pl-pds" style="box-sizing: border-box;">"</span>/some-dummy-url<span class="pl-pds" style="box-sizing: border-box;">"</span></span>
}</pre>
</div>

That's a cool error response. It contains all the details that are typically needed.

