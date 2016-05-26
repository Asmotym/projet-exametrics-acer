# Android Admin APP

## Arboréscences des fichiers

- [activities]() Contient toutes les activité de l'application
  - [AddNote]() Ajout d'un note
  - [AreaListView]() Liste des zones
  - [DetailsNote]() Détails d'une note
  - [MapsActivity]() Map pour placer créer des zones
  - [NoteListView]() Liste des notes d'une zone
- [classes]() Toutes les classe nécéssaire au fonctionnement de l'application
  - [adapters]() Les adapteurs pour les ListView
    - [AreaAdapter]() Adapteur pour les zones
    - [NoteAdapter]() Adapteur pour les notes
  - [classes]() Les classes principales
    - [Area]() Classe pour les zones
    - [Note]() Classe pour les notes
    - [Point]() Classe pour les points
  - [click_listeners]() Listeners pour les listes
    - [OnAreaItemClickListener]() Listener pour le clique sur une zone
    - [OnNoteItemClickListener]() Listener pour le clique sur une note
  - [realm_classes]() Classes Realm
    - [RealmArea]() Classe Realm pour les zones
    - [RealmNote]() Classes Realm pour les notes
    - [RealmPoint]() Classe Realm pour les points
- [controllers]() Controlleurs pour les téléchargements des données
  - [AreaController]() Controlleur pour les zones
  - [NoteController]() Controlleur pour les notes
  - [PointController]() Controlleur pour les points
- [tools]() Classes utilitaire
  - [GlobalVariables]() Variables globale
  - [RealmConfig]() Classe d'instance Realm

