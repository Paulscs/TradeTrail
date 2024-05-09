# TradeTrail

TradeTrail is an online marketplace application built with Java EE (Enterprise Edition) technologies. It provides users with a platform to buy and sell various items, facilitating transactions between buyers and sellers.

## Features

- **User Authentication:** Users can register, log in, and log out securely. Admin users have access to additional features like managing products.
- **Product Management:** Admin users can add, edit, and delete products. Regular users can browse products, view details, and make purchases.
- **Responsive Design:** The application is designed to be responsive, ensuring a seamless experience across devices of all sizes.
- **Database Integration:** TradeTrail integrates with a database backend for storing user information, product details, and transaction history.

## Technologies Used

- **Java EE:** Utilized for building the backend logic and handling server-side operations.
- **Jakarta Servlets:** Used for handling HTTP requests and responses, implementing the application's servlets.
- **JSP (JavaServer Pages):** Employed for generating dynamic web content and rendering HTML pages with embedded Java code.
- **MySQL:** Chosen as the database management system for storing user data, product information, and other application data.
- **HTML/CSS/JavaScript:** Utilized for building the frontend interface and implementing client-side interactivity.
- **Bootstrap:** Leveraged for designing responsive and mobile-friendly web pages with pre-designed components.
- **jQuery:** Used for simplifying DOM manipulation, event handling, and making AJAX requests to the server.

## Setup Instructions

1. **Clone the Repository:** Clone the TradeTrail repository to your local machine using the following command:
   ```bash
   git clone https://github.com/Paulscs/TradeTrail.git

2. **Database Configuration:** Set up a MySQL database and update the database connection settings in the application's configuration files.

3. **Build and Deploy:** Build the project using your preferred IDE or build tool (I personally used Intellij IDEA Ultimate). Deploy the application to a Java EE server, I used Apache Tomcat 10.1.20.

4. **Access the Application:** Once deployed, access the application through your web browser using the appropriate URL.

## Usage

- **Register/Login:** Create a new account or log in with existing credentials.
- **Browse Products:** Explore the available products listed on the marketplace.
- **Sell Items:** Add new items to the marketplace for other users to purchase.
- **Admin Dashboard:** Access additional features like managing products if logged in as an admin user.
