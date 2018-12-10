# flexiana-scramble

Flexiana scramble challenge.
For details look into [conditions](./doc/challenge.md).

## Usage

To run server you need to install [Leiningen](https://leiningen.org/)

    $ lein cljsbuild once
    $ lein run
    
or

    $ lein cljsbuild once
    $ lein uberjar
    $ java -jar ./target/uberjar/flexiana-scramble.jar

## Options

Port can be configured with `PORT` environment variable.
Default value is `8888`.

### TODO

Application less than ideal, so it need to added:

* ClojureScript tests
* Handle timeout in scramble request
* Better explanation on HTTP errors
* Configuration + config validation
* Proper documentation with examples
* Scramble timings - it may give hint to better solution
* Request logging

## License

Copyright © 2018 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
