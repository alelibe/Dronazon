# Dronazon

<img src="unimi_logo.jpg" alt="LOGO" width="100" align="right"/>

- **Author:** Alessia Libertucci
- **Course:** Distributed and Pervasive Systems (A.A. 2020/2021) 


## Description
The project implements a system that manages the deliveries of orders received from the Dronazon e-commerce site, through the use of drones. Figure below shows the architecture of the developed system.

<img src="dronazon_architecture.png" width="500" align="center"/>

Dronazon has at its disposal a flock of drones distributed within a smart-city to manage its deliveries. Every time a Dronazon customer places a new order, this information will be passed to the smart-city master drone, which will determine which drone will take care of that delivery.
Periodically, the drones communicate to a remote server, called the Administrator Server, information relating to the number of deliveries made, the number of kilometers travelled, the level of air pollution detected and the remaining battery level.

As a result, Dronazon administrators can obtain information on drone and delivery statistics through the Admin Server. Furthermore, through the Server Administrator it is also possible to register and remove drones from the system dynamically.
