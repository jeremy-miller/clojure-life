FROM clojure:alpine
RUN apk --no-cache add curl
RUN mkdir -p /usr/src/app
RUN mkdir -p /cov
WORKDIR /usr/src/app
COPY project.clj /usr/src/app
RUN lein deps
COPY . /usr/src/app
RUN mv "$(lein uberjar | sed -n 's/^Created \(.*standalone\.jar\)/\1/p')" app-standalone.jar
CMD ["java", "-jar", "app-standalone.jar"]
