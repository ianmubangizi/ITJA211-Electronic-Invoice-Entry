# Electronic Invoice Entry

This is my Second Year, First Semester [ITJA211] - (Programming in Java)
assignment Question 1

## Getting Started

-->[TODO]

### Prerequisites

You need to have basic understanding of basic Java programming concepts such as
types, objects, lambda functions as well as basic Object Oriented Design patterns
like the Singleton and Strategy pattern.

Windows:
If your lazy like me use a package manage like Ninite or Chocolatey to get all the software:

Run all as Admin

Install chocolatey

```cmd
@"%SystemRoot%\System32\WindowsPowerShell\v1.0\powershell.exe" -NoProfile -InputFormat None -ExecutionPolicy Bypass -Command "iex ((New-Object System.Net.WebClient).DownloadString('https://chocolatey.org/install.ps1'))" && SET "PATH=%PATH%;%ALLUSERSPROFILE%\chocolatey\bin"
```

Install need tools

```cmd
C:\WINDOWS\system32>choco install -y jre8 jdk8 netbeans mysql Bitnami-XAMPP datagrip 
```

Change fields to your Local Settings in DatabaseService.java

```java
    package com.electronic_invoice.Services;
    ....
    public final class DatabaseService {

        private final String dbuser = "username";
        private final String dbpassword = "password"; // use [ ""; ] for no password
        private final String jdbc_driver = "com.mysql.cj.jdbc.Driver"; // use [ "com.mysql.jdbc.Driver"; ] for older mysql-connector-java
        private final String url = "jdbc:mysql://localhost:3306/databasename";
        ....
    }
```



## Built With

* [Java JDK 1.8](https://www.oracle.com/technetwork/es/java/javase/downloads/jdk8-downloads-2133151.html) - Java Development Kit
* [Maven](https://maven.apache.org/) - Dependency Management
* [MySQL](https://www.mysql.com/) - Persistence Layer
* [mysql-connector](https://dev.mysql.com/downloads/connector/j/8.0.html) - JDBC Client Driver to make use of MySQL database
* [DataGrip](https://www.jetbrains.com/datagrip/) - Made writing SQL fun U+1F607 for once U+1F923
* [NetBeans IDE 8.0.2](https://netbeans.org/downloads/8.0.2/) - Main IDE I used , almost has it all which is a problem
                         U+1F611, given that I only have an Intel i5 & 8GB RAM
* [Visual Studio Code](https://code.visualstudio.com/) - When I just couldn't wait for NetBeans to load modules U+1F62A

## Author

* **Ian Mubangizi** - ** - [GitHub](https://github.com/ianmubangizi) [Website](https://www.ianmubangizi.com) [Email](io@ianmubangizi.com)
