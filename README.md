# Parking Reservation App 

## Project Context

Traffic violations related to improper parking and chaotic stops cause disruptions in traffic flow and exacerbate congestion in large cities. One of the reasons for this increase is the difficulty drivers face in finding available parking spaces. An emerging solution to this problem is online parking reservation. This alternative offers several advantages, providing drivers with the ability to park close to their destinations.

## Key Features

- Account Creation: The application allows a user to create an account.

- Authentication: Users can authenticate using their created account or with their Gmail account.

- Parking List Display: A list view of available parking lots, with each item containing information such as a photo, name, and location.

- Map View of Parkings: This feature displays available parking lots on a map.

- Parking Details Display: Detailed information about a parking lot, including those displayed in the list view as well as additional information like a description.

- Reservation of a Parking Space: To reserve a space, the driver specifies the date, entry and exit times, and confirms payment. A confirmation with the reservation number, parking space number, and a QR code representing the reservation number is then displayed.

- My Reservations: Users can view a list of their reservations offline. This offline mode allows them to scan the QR code at the parking entrance and display their parking space without being connected to the internet.

- Push Notifications: Sending reminders to drivers via push notifications to remind them of their upcoming reservations.

## Technologies

- Kotlin: The programming language used for developing the application, known for its modern features and interoperability with Java.

- Jetpack Compose: The toolkit for building native UI for Android applications, providing a declarative way to design UIs and manage states.

- Firebase: A comprehensive mobile and web application development platform provided by Google. It offers a suite of tools and services, including real-time databases, authentication, cloud storage, and more. Firebase is commonly used for backend services in mobile apps.

## Installation

- Clone the Repository.

- Set Up Firebase Project:
    - Create a Firebase project and add your app to it.
    - Download the `google-services.json` file and place it in the `app` directory.
  
- Navigate to the project directory and run the following command to install the required dependencies: `./gradlew build`

- Connect your Android or iOS device to your computer, or use an emulator/simulator. Run the app using: `./gradlew installDebug`
