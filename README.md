# 💽 Multilevel Cache Simulator

A fully configurable multilevel cache simulator written in Java.  
Designed to help understand how memory hierarchies work and how performance is affected by different cache configurations.

## ✨ Features

- ✅ Configurable single-level cache (L1) using JSON input
- ✅ Memory trace loader with support for decimal and hex formats
- ✅ Hit/miss tracking and summary statistics (per cache level)
- ✅ Modular and extensible codebase, structured by purpose (config, utils, core)

- 🔜 Support for multilevel caches (L1 → L2 → L3)
- 🔜 Replacement policy abstraction (e.g. LRU, PLRU, RRIP)
- 🔜 Average Memory Access Time (AMAT) calculation
- 🔜 Visual representation of cache hierarchy and data flow (GUI or CLI diagrams)

## ✅ Technologies

- Java 21
- Maven
- JUnit 5

## 🚀 Usage

### 🛠️ To compile

```bash
mvn clean install
```

### ▶️ To run

```bash
mvn exec:java -Dexec.mainClass="cache.Main" -Dexec.args="cache-config.json traces/sample_trace.txt"
```

## 🧾 Example Output

```bash
✔️ Cache configuration loaded successfully!
✔️ Trace loaded: 5 addresses.

Access 0x1A2B3C => MISS
Access 0x2A => MISS
Access 0xDEADBEEF => MISS
Access 0x64 => MISS
Access 0x0 => HIT

=== Simulation Complete ===
Total accesses: 5
Hits: 1
Misses: 4
Hit rate: 20.00%
```

## 📁 File Structure

```bash
├── src/
│   └── main/java/cache/
│       ├── Main.java
│       ├── config/
│       │   ├── CacheConfig.java
│       │   └── CacheLevelConfig.java
│       ├── simulator/
│       │   └── CacheLevel.java
│       └── utils/
│           └── TraceReader.java
├── traces/
│   └── sample_trace.txt
├── cache-config.json
├── README.md
├── LICENSE
└── pom.xml
```

## 📜 License

This project is licensed under the MIT License.
