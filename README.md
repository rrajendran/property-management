#Property Management [![Build Status](https://travis-ci.org/rrajendran/property-management.svg)](https://travis-ci.org/rrajendran/property-management)

Managing application properties using zookeeper. All properties loaded into zookeeper, which is stored as nodes as key value pairs. By this it enables manage configs for multiple enviornments at one place. Updating properies is done in zookeeper, which in turn updates the application through WatcherService which is provided by zookeeper.

#Technology Stack
* Java
* Google guice
* Zookeeper
* Rest (Test application for property testing)

