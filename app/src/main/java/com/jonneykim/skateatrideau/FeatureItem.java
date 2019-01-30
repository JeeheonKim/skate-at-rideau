package com.jonneykim.skateatrideau;

/*
    {
      "type": "Feature",
      "id": 22,
      "geometry": null,
      "properties": {
        "OBJECTID": 22,
        "Status": "Good",
        "Status_FR": "Bon",
        "Maint_Time": "ongoing",
        "Maint_Time_FR": "en cours",
        "Maint_Type": "Sweeping",
        "Maint_Type_FR": "Balayage",
        "To_": "Pig Island",
        "From_": "Fifth Ave",
        "De_": "L'avenue Fifth",
        "A_": "L'île Pig",
        "ID": 7,
        "Length_KM": "0.7 km",
        "Notes": null,
        "Notes_FR": null,
        "Longueur": "0.7 km",
        "Shape__Area": 68286.7999267578,
        "Shape__Length": 1616.39121179068
      }
    }
 */

public class FeatureItem {
    String type;
    int id;
    Object geometry;
    RinkProperty properties;
}
