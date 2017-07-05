[![Build Status](https://travis-ci.org/jeremy-miller/life-clojure.svg?branch=master)](https://travis-ci.org/jeremy-miller/life-clojure)
[![Test Coverage](https://coveralls.io/repos/github/jeremy-miller/life-clojure/badge.svg?branch=master)](https://coveralls.io/github/jeremy-miller/life-clojure?branch=master)
[![Code Climate](https://codeclimate.com/github/jeremy-miller/life-clojure/badges/gpa.svg)](https://codeclimate.com/github/jeremy-miller/life-clojure)
[![MIT Licensed](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/hyperium/hyper/master/LICENSE)
[![Clojure Version](https://img.shields.io/badge/Clojure-1.8.0-blue.svg)]()

# Life (in Clojure)
Clojure implementation of [Conway's Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life).

![Usage](https://github.com/jeremy-miller/life-clojure/blob/master/usage.gif)

## Usage
This implementation uses a Docker container to isolate the execution environment.

### Prerequisites
- [Docker](https://docs.docker.com/engine/installation/)

### Configuration
To configure the Life game, modify the ```configuration-name``` variable in *src/life_clojure/core.clj*.

### Setup
Before interacting with the Life game, the Docker container must be built: ```docker build -t jeremymiller/life-clojure .```

### Automatic Code Formatting and Static Code Analysis
To run [cljfmt](https://github.com/weavejester/cljfmt) code formatter and *check*, [kibit](https://github.com/jonase/kibit), and [eastwood](https://github.com/jonase/eastwood) static code analyzers,
execute the following command: ```docker run -it --rm jeremymiller/life-clojure lein omni```

### Test
To run the Life tests, execute the following command: ```docker run -it --rm jeremymiller/life-clojure lein test```

### Run
To run the Life game, execute the following command: ```docker run -it --rm  jeremymiller/life-clojure```

When running the Life game, it will output the updated grid in the terminal.
