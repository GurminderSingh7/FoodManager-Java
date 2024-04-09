<!-- Aditya Hirpara -->


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="Controller.InventoryItem,java.util.List,DAO.InventoryItemDAO" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inventory List for Charity</title>
    <!-- External Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
    <!-- External Icon Library -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/remixicon/3.5.0/remixicon.css" crossorigin="">
    <!-- Internal Styles -->
    <style>
        /* CSS Variables */
        :root {
            --white-color: #fff;
            --black-color: #000;
            --body-font: "Poppins", sans-serif;
            --normal-font-size: 1rem;
            --small-font-size: .813rem;
            --border-color: #ccc;
            --table-bg-color: rgba(255, 255, 255, 0.9);
            --button-bg-color: #28a745;
            --button-hover-bg-color: #218838;
            --link-color: #007bff;
            --link-hover-color: #0056b3;
            --surplus-bg-color: #FFCCCB;
            --th-bg-color: rgba(0, 123, 255, 0.5);
        }

        /* Base Styles */
        * {
            box-sizing: border-box;
            padding: 0;
            margin: 0;
        }

        html, body {
            width: 100%;
            min-height: 100vh;
            margin: 0;
            padding: 0;
            font-family: var(--body-font);
            font-size: var(--normal-font-size);
            background-image: url('bg.png');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            color: var(--black-color);
            position: relative;
        }

        h2 {
            font-size: 3rem;
            font-weight: bold;
            text-transform: uppercase;
            letter-spacing: 2px;
            text-align: center;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2);
            margin: 2rem 0;
        }

        h3 {
            font-size: 2rem;
            font-weight: bold;
            text-transform: uppercase;
            letter-spacing: 1px;
            text-align: center;
            margin: 1rem 0;
        }

        .table-container {
            width: 90%;
            max-width: 800px;
            max-height: 70vh;
            overflow-y: auto;
            margin: 20px auto;
            border-radius: 1rem;
            backdrop-filter: blur(10px);
            border: 2px solid var(--border-color);
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            z-index: 2;
            background: transparent;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: transparent;
            margin-bottom: 2rem;
        }

        th, td {
            padding: 12px;
            background-color: transparent;
            color: var(--black-color);
            border-bottom: 1px solid var(--border-color);
        }

        th {
            position: sticky;
            top: 0;
            background-color: var(--th-bg-color);
            color: var(--white-color);
        }

        thead th {
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }

        tbody {
            border: none;
        }

        tbody tr:nth-child(odd) {
            background-color: rgba(255, 255, 255, 0.3);
        }

        tbody tr:nth-child(even) {
            background-color: rgba(255, 255, 255, 0.5);
        }

        tbody tr:last-child td:first-child {
            border-bottom-left-radius: 1rem;
        }

        tbody tr:last-child td:last-child {
            border-bottom-right-radius: 1rem;
        }

        tbody tr:hover {
            background-color: rgba(255, 255, 255, 0.7);
        }

        .surplus {
            background-color: var(--surplus-bg-color);
        }

        a {
            color: var(--link-color);
            text-decoration: none;
            transition: color 0.3s ease;
        }

        a:hover {
            color: var(--link-hover-color);
        }

        .claim-button {
            background-color: var(--button-bg-color);
            color: var(--white-color);
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 0.5rem;
            transition: background-color 0.3s;
            text-align: center;
            display: inline-block;
        }

        .claim-button:hover {
            background-color: var(--button-hover-bg-color);
        }

        .subscribe {
            margin-top: 20px;
            text-align: center;
        }

        .subscribe-button {
            background-color: var(--button-bg-color);
            color: var(--white-color);
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 0.5rem;
            transition: background-color 0.3s;
            text-align: center;
            display: inline-block;
            text-decoration: none;
        }

        .subscribe-button:hover {
            background-color: var(--button-hover-bg-color);
        }

        .logout {
            position: absolute;
            top: 20px; /* Adjust the distance from the top */
            right: 20px; /* Adjust the distance from the right */
        }

        .logout input[type="submit"] {
            background-color: var(--button-bg-color);
            color: var(--white-color);
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 0.5rem; /* Adjusted border radius */
            transition: background-color 0.3s;
            text-align: center;
            display: inline-block;
        }

        .logout input[type="submit"]:hover {
            background-color: var(--button-hover-bg-color);
        }
    </style>
</head>
<body>
    <!-- Logout Button -->
    <div class="logout">
        <form action="LogoutServlet" method="get">
            <input type="submit" value="Logout">
        </form>
    </div>
    
    <!-- Page Heading -->
    <h2>Inventory List for Charity</h2>
    <h3>Surplus Food Items</h3>
    
    <!-- Table Container -->
    <div class="table-container">
        <table>
            <thead>
                <tr>
                    <th>Item ID</th>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Expiration Date</th>
                    <th>Price</th>
                    <th>Action</th>
                    <th>Claim</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    // Instantiate InventoryItemDAO
                    InventoryItemDAO dao = new InventoryItemDAO();
                    // Get the list of surplus items excluding Discounted Sale items
                    List<InventoryItem> surplusItems = dao.getDonationItemsForCharity();
                    // Iterate over surplus items and generate table rows
                    for (InventoryItem item : surplusItems) { 
                %>
                <tr class="surplus">
                    <td><%= item.getItemId() %></td>
                    <td><%= item.getName() %></td>
                    <td><%= item.getQuantity() %></td>
                    <td><%= item.getExpirationDate() %></td>
                    <td>$<%= item.getPrice() %></td>
                    <td><%= item.getAction() %></td>
                    <td><button class="claim-button" onclick="purchaseItem('<%= item.getItemId() %>')">Claim</button></td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>

    <!-- Subscribe Button -->
    <div class="subscribe">
        <a href="subscription.html"><button class="subscribe-button">Subscribe to Alerts</button></a>
    </div>

    <!-- JavaScript for Item Claim -->
    <script>
        function purchaseItem(itemId) {
            var xhr = new XMLHttpRequest();
            xhr.open('GET', 'DeleteInventoryItemServlet?id=' + itemId, true);
            xhr.send();
            xhr.onreadystatechange = function() {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    location.reload(); // Reload the page after claiming the item
                    alert('Item claimed successfully!'); // Display an alert
                }
            };
        }
    </script>
</body>
</html>