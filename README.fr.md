# Pylox

[![readme](https://img.shields.io/badge/lang-en-red.svg)](README.md)
[![licence](https://img.shields.io/badge/licence-MIT-yellow.svg)](LICENSE)
[![discord](https://img.shields.io/badge/Discord-7289DA.svg)](https://discord.gg/qaSjwkR7MU)

**Ergonomique**, **intuitif** et **économique**, ce périphérique d'ordinateur vous offre la possibilité de pré-enregistrer des **macros** pour les avoir **à porter de main**, **en un seul clic**.

## Table des matières

- [De quoi s'agit-il ?](#de-quoi-sagit-il-)
- [Comment obtenir Pylox ?](#comment-obtenir-pylox)
- [Arborescence du dépôt](#arborescence-du-dépôt)
- [Licence](#licence)

## De quoi s'agit-il ?

**Pylox** est un écosystème **matériel** et **logiciel** conçu pour permettre à tout·e passionné·e d'informatique d'automatiser des tâches, de lancer des macros, de contrôler des logiciels et de créer une interface tactile dédiée. Le projet englobe **PyloxDeck** et **PyloxDesktop**, qui sont respectivement le périphérique (hardware + firmware) et l'application dédiée (software).

Pensé pour être simple à fabriquer, mais puissant à utiliser, Pylox est évolutif, c'est à dire que chacun.e peut **s'approprier** le projet et **exploiter** ce dont-il.elle a besoin pour développer sa propre solution.

## Comment obtenir Pylox

Pour construire ce périphérique, ce dépôt met à disposition tous les outils nécessaires :

- [**Hardware**](/hardware/) : Schémas électroniques, circuits, PCB, et designs 3D (Fritzing, KiCad, FreeCAD) dédiés à la fabrication du périphérique
- [**Firmware**](/firmware/) : Codes sources (Python) pour le microcontrôleur
- [**Software**](/software/) : Applications graphiques et logiciels (Java)

## Arborescence du dépôt

```plaintext
Pylox/
├── README.md
├── README.fr.md
├── LICENSE
├── hardware/
│   ├── PyloxDeck/
│   │   ├── schematics/
│   │   └── 3D/
│   └── PyloxDeck+/
│       └── 3D/
├── firmware/
|   ├── v1/
|   ├── v2/
│   └── v2.1/
└── software/
    └── PyloxDesktop/
```

## Licence

Ce projet est sous **Licence MIT**. Vous pouvez librement l'utiliser, le modifier et le redistribuer à condition de mentionner l'auteur original.

Pour plus d'informations, consultez le fichier [LICENSE](./LICENSE).
