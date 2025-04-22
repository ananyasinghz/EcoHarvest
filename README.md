<h1> Food Waste Reduction Platform </h1>

<h2>Summary</h2>
This project involves the development of a Food Waste Reduction Platform, built using modern web technologies, with features such as:

<ul>
  <li>Donation and Request System: Users can donate surplus food or request food supplies based on availability.</li>
  <li>Inventory Tracking: Helps both donors and recipients manage the food items, reducing unnecessary waste.</li>
  <li>User Authentication: Secure login and user management to facilitate trusted interactions.</li>
  <li>Notifications: Automated alerts for available food and requests.</li>
</ul>
The project encourages collaboration between individuals, businesses, and non-profit organizations to combat food waste while improving community support.

<h2>Introduction</h2>
The Food Waste Reduction Platform is an innovative web-based solution aimed at addressing the global issue of food waste. This platform connects individuals and organizations with excess food to those in need, helping to minimize waste while promoting sustainability and food security. It provides a seamless and user-friendly interface where users can donate, request, and track food supplies, fostering a community-driven approach to waste reduction.

<h2>Functional requirements</h2>

<h3>FR – 01: Users Registration:</h3>
<ul><strong>Registration:</strong>Users must have the ability to create accounts on the platform by providing their name, email, password, and selecting the user type (i.e., retailers, consumers, or charitable organizations).</ul>
<ul><strong>Authentication</strong>The platform must have to login/logout of the functionality</ul>

<h3>FR – 02: Retailers:</h3>
<ul><strong>Inventory Management: </strong>Retailers must have the capability to manage their inventory of food items, including adding new items, updating quantities, and setting expiration dates for each item.</ul>
<ul><strong>Surplus Food Identification: </strong>Retailers should be able to identify and flag surplus food items that are nearing expiration or are in excess of demand. Surplus items are those with expiry dates within the next one week.</ul>
<ul><strong>Listing Surplus Food Items: </strong>Retailers must have the functionality to list surplus food items on the platform for donation or sale at a discounted price. An item is up for Donation or for sale at a reduced price is determined by the Retailer.</ul>

<h3>FR- 03: Charitable Organization:</h3>
<ul><strong>Claim Food: </strong>Charitable organizations such as Food Banks/Missions should be able to claim the food item listed by retailers available for donation.</ul>
<ul><strong>Update Inventory: </strong>The retailer inventory must be updated accordingly once a particular food item is claimed by a Charitable Organization.</ul>

<h3>FR-04: Consumers:</h3>
<ul><strong>a. Purchase: </strong>Consumers of the platform should be able to purchase (not real transaction) the items listed by the retailers at a given discount rate.</ul>
<ul><strong>Update Inventory: </strong>The retailer inventory must be updated accordingly once a particular food item is purchased by a consumer.</ul>

<h3>FR-05: Surplus Food Alert:</h3>
<ul><strong>User Subscription: </strong>Users can subscribe to receive surplus food alerts based on their location, communication method (email or phone) and food preferences</ul>
<ul><strong>Automatic Notifications: </strong>Whenever retailers list surplus food items on the platform, users subscribed to alerts receive automatic notifications via email or phone</ul>

<h2>Application Architecture</h2>

![Food Waste Reduction Platform](README/Diagram02.jpg)

<h3>Front-end (Presentation Layer)</h3>
The front-end of the FWRP comprises JSP (JavaServer Pages) for dynamic web page generation and user interaction.
JSP pages provide the user interface for registration, login/logout, browsing surplus food items, and managing user subscriptions.
Servlets act as our controller, it is responsible for being the mediary between our Model and View.

<h3>Back-end (Business Logic Layer)</h3>
Back end is composed of java code that will act as the model in the MVC pattern. 
Model will interact with the Servlet to talk with the front end JSP’s.
The Java code will be dealing with all our logic, for example providing the sale price of items that have a discount on them or providing prepared statements to store data in the DB.
We plan to implement different design patterns during our implementation like abstract factory for generating users, builder pattern for initializing users, singleton class for validation, and DAO for CRUD operations for our database.

<h3>Database (Data Layer)</h3>
The FWRP relies on a relational database management system (RDBMS), named FWRP, for data storage and management.
MySQL can be utilized as the underlying RDBMS to store information related to users, retailers, food items, subscriptions, and transactions.
The database schema includes entities (tables) such as ‘users’, ‘transaction’, ‘charity_inventory’, ‘user_inventory’, ‘item’, and ‘user_account’ structured to support the platform's functionalities.

<h3>Integration</h3>
Integration between the front-end and back-end is achieved through HTTP requests and responses.
JSP pages communicate with Servlets using HTTP protocols, exchanging data and triggering server-side actions.
Servlets interact with the database using JDBC (Java Database Connectivity), executing SQL queries to retrieve, update, and manipulate data.

<h2>Business Architecture</h2>

![Food Waste Reduction Platform](README/Diagram01.jpg)

Registration:
<ol>
  <li><strong>Retailers:</strong> Retailers play a crucial role in our platform by managing surplus food items. They have the ability to add new items, update quantities, set expiration dates, and specify whether the items are available for donation or sale at a reduced price. Additionally, retailers can easily flag surplus food items that are nearing expiration or are in excess of demand. Once flagged, these surplus items can be promptly listed on the platform for donation or sale.</li>
  <li><strong>Charitable Organization:</strong> Charitable organizations are empowered to seamlessly claim surplus food items listed by retailers and promptly update their inventory accordingly. By simply selecting the desired items, charitable organizations can efficiently claim the available surplus food, ensuring that it reaches those in need.</li>
  <li><strong>Consumers:</strong> Consumers have the opportunity to purchase surplus food items at discounted prices through this system. By browsing the available listings, consumers can easily identify and select items they wish to purchase at reduced rates.</li>
</ol>

<h3>Database Structure</h3>

![Food Waste Reduction Platform](README/Diagram03.jpg)

Our database shall have 6 tables
<ol>
    <li><strong>Users:</strong> This table has general user details like name, email, phone, location, and password along with the user type (charity, consumer, retailer), and user preferences for subscription.</li> 
    <li><strong>Item:</strong>This table has details about food items and their category (poultry, dairy, etc.).</li>
    <li><strong>User_inventory:</strong> This table contains the details about retailer’s inventory. It has details regarding batch number, batch expiry date, quantity, unit price, and final price.</li>
    <li><strong>Transaction: </strong>This table shall concern the transactions that take place once a consumer buys from a retailer. The quantity purchased shall be decreased from the retailer’s inventory.</li>
    <li><strong>Charity inventory:</strong>This table shall contain details of the stock held by charities to be given as donation.</li>
    <li><strong>UserAccount</strong>This table contains details about the account balance of customers.</li>
</ol>

<h2>Security Architecture </h2>
<ul>
  <li>Users will have an account with a balance, we will perform validity checks on their stored, store credit to make sure that purchases are valid.</li>
  <li>The passwords are stored in a plain text format in the database. Ideally, they should be encoded to safeguard user information. However, outsiders can’t access our database without credentials.</li>
  <li>SQL statements are prepared using preparedStatement() so that users cannot SQL inject to pry at, or manipulate our database.</li>
  <li>Users will only interact with the GUI and won’t have any interaction with the JAVA code.</li>
</ul>

<h2>Deployment Architecture</h2>

![Food Waste Reduction Platform](README/Diagram04.jpg)

<ul>
  <li>Laptop with Java: A device running a Java application.</li>
  <li>Web Server with a Website: A server hosting a website, which can be accessed by the user client.</li>
  <li>DB Server with MySQL DB: The database server running MySQL, which stores and manages data for the website.</li>
  <li>User Client with an HTML Browser: A user’s device with a web browser, used to access the website hosted on the web server.</li>
</ul>



<h2>Created By</h2> 
<ul>
  <li>Ananya Singh</li>
  <li>Aditi Pandey</li>
  <li>Palak Ramani</li>
</ul>

High Level Design : 
https://docs.google.com/document/d/1SkjawkOkLsN35J8bnhqSzhr_HtTyTBvO67HUS49Nnhg/edit?usp=sharing


