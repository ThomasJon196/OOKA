# OOKA - Proxy Pattern

Documentation inside [Docs](documentation/docs.md)


## Setup and Running

```bash
# Init VPN access (required for the usage of the database)
sudo openvpn client.conf

# Execute main class specified in pom.xml
mvn exec:java

# Build .jar file
mvn package

# Execute .jar file
java -jar <path-to-jar>
```

> Accessing VPN requires the client.conf file to be present in the project folder. (Downloadable via https://ux-2s18.inf.h-brs.de/faq/vpn)