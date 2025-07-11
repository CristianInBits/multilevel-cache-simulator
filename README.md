# Multilevel Cache Simulator

A fully configurable multilevel cache simulator written in Java.  
Designed to help understand how memory hierarchies work and how performance is affected by different cache configurations.

## Features

- Customizable multi-level cache structure (L1, L2, L3)
- Support for loading memory access traces
- Performance metrics: hit/miss rate, AMAT
- Designed for extensibility and modularity
- (Planned) Visual representation of cache access behavior

## Technologies

- Java 21
- Maven
- JUnit 5

## Usage

### To compile

```bash
mvn clean install
```

### To run

```bash
mvn exec:java -Dexec.mainClass="cache.Main"
```

## License

This project is licensed under the MIT License.
