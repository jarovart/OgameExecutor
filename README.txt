# OgameExecutor – Automated OGame Fleet Controller

OgameExecutor is an automation tool for the online browser game **OGame**.  
It automatically manages fleet expeditions, transportation missions, resource transfers, and
various navigation steps using Selenium WebDriver.

This project is written in **Java 21**, uses **Selenium** and **Serenity PageObjects**, and can be packaged
as a standalone executable JAR.

---

## 🚀 Features

### ✔ Automated Expeditions
Automatically detects free fleet slots, parses active fleets, and launches new expeditions.

### ✔ Supports All Ship Types
Military and civil ships, customizable via configuration.

### ✔ Browser Modes
- **Headless mode** (no UI)
- **Visible mode** (debugging)

### ✔ Timestamp & Return-Time Calculation
Converts OGame time strings into real Java `Timestamp` objects.

### ✔ Configurable Behavior
Reads settings from command-line or a config file:

[Login, user@email.com]
[Password, ********]
[ExpoPlanetNumber, 1]
[ServerName, Last]
[OnlyCivilShips, Yes]
[maxKTransporter, 15000]
[maxGTransporter, 6200]
[OnlyHeadless, Yes]


---

## 📦 Installation

### Prerequisites

| Component | Version |
|----------|---------|
| Java     | **21+** |
| Maven    | **3.8+** |
| Firefox or Chrome | latest version |
| WebDriver | Automatically handled by Selenium |

Clone the repository:

```bash
git clone https://github.com/<your-username>/OgameExecutor.git
cd OgameExecutor
🛠 Build Instructions
Create a runnable JAR
mvn clean package -DskipTests

This generates:
target/ogameExecutor-1.0-SNAPSHOT-shaded.jar

▶️ Running the Program

Normal mode (browser visible)
java -jar ogameExecutor-1.0-SNAPSHOT-shaded.jar

Headless mode
java -jar ogameExecutor-1.0-SNAPSHOT-shaded.jar --headless

📝 Configuration

The program accepts configuration parameters from command-line or an external file.

Example .config:
[Login, user@email.com]
[Password, 123456]
[ExpoPlanetNumber, 2]
[ServerName, Andromeda]
[OnlyCivilShips, No]
[maxKTransporter, 3000]
[maxGTransporter, 1500]
[OnlyHeadless, Yes]

🧠 Architecture Overview
Logic/
Contains the entry point OgameExec.java and core automation logic.
Controller/
LoginController
FleetController
MenuController
ResourcesController
Presentation/
Serenity PageObjects handling Selenium interactions.
Data/
Enums representing fleet types, attack types, etc.

⚙ Browser Drivers
Firefox
Automatically resolved using Selenium Manager, or manually:
System.setProperty("webdriver.gecko.driver", "path/to/geckodriver.exe");

📦 Releases
You can use GitHub Actions to automatically build, package, and upload:
ogameExecutor-<version>.jar
Release notes (release.md)

🐞 Known Issues
GraalVM native image support is experimental and currently not recommended.
Selenium Manager may require manual driver paths on some systems.
Serenity components are not fully compatible with native-image builds.

🤝 Contributing
Fork the repository
Create your feature branch
Commit changes
Open a Pull Request
Contributions are welcome!

📄 License
This project is licensed under the MIT License.
See the LICENSE file for details.
