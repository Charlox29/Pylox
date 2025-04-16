# Pylox

[![readme.fr](https://img.shields.io/badge/lang-fr-blue.svg)](README.fr.md)
[![license](https://img.shields.io/badge/license-MIT-yellow.svg)](LICENSE)
[![discord](https://img.shields.io/badge/Discord-7289DA.svg)](https://discord.gg/qaSjwkR7MU)

**Ergonomic**, **intuitive** and **affordable**, this computer peripheral gives you the ability to pre-record **macros** so you have them **at your fingertips**, **with a single click**.

## Table of Contents

- [What is it?](#what-is-it)
- [How to Get Pylox](#how-to-get-pylox)
- [Repository Structure](#repository-structure)
- [License](#license)

## What is it?

**Pylox** is a **physical** and **digital** ecosystem designed to empower every computing enthusiast to automate tasks, launch macros, control applications, and create a dedicated touch interface. The project encompasses **PyloxDeck** and **PyloxDesktop**, which are respectively the device (hardware + firmware) and the dedicated application (software).

Designed to be simple to build yet powerful to use, Pylox is scalable – meaning anyone can **customize** the project and **utilize** what they need to develop their own solution.

## How to Get Pylox

To build this device, this repository provides all the necessary resources:

- [**Hardware**](/hardware/) : Electronic schematics, circuits, PCB layouts, and 3D designs (Fritzing, KiCad, FreeCAD) dedicated to fabricating the peripheral.
- [**Firmware**](/firmware/) : Source code (Python) for the microcontroller.
- [**Software**](/software/) : Graphical applications and software (Java) to control and configure the device.

## Repository Structure

```plaintext
Pylox/
├── README.md
├── README.fr.md
├── LICENSE
├── hardware/
│   └── PyloxDeck/
│       ├── schematics/
│       └── 3D/
├── firmware/
|   ├── v1/
│   └── v2/
└── software/
    └── PyloxDesktop/
```

## License

This project is distributed under the **MIT License**. You are free to use, modify, and redistribute it provided that you give credit to the original author.

For more information, please refer to the [LICENSE](./LICENSE) file.
