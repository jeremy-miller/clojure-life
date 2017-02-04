# Life (in Clojure)
Clojure implementation of [Conway's Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life).

## Usage
To interact with the Life game, follow the steps below.

*NOTE: This game has only been tested using OpenJDK 1.8 on Ubuntu 16.10.*

### Prerequisites
- [OpenJDK 1.8](http://openjdk.java.net/)
- [Leiningen](https://leiningen.org/)
- [Docker](https://docs.docker.com/engine/installation/linux/ubuntu/)

### Setup
Before interacting with the Life game, the Docker container must be built: ```docker build -t jeremymiller/life-clojure .```

### Static Code Analysis
To run *check*, [kibit](https://github.com/jonase/kibit), and [eastwood](https://github.com/jonase/eastwood) static code analyzers,
execute the following command: ```docker run -it --rm jeremymiller/life-clojure lein with-profile dev check && lein with-profile dev kibit && lein with-profile dev eastwood```

### Run
To run the Life game, execute the following command: ```java -jar target/uberjar/life-clojure-0.1.0-SNAPSHOT-standalone.jar```
