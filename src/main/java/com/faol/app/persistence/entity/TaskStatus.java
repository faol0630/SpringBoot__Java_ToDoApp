package com.faol.app.persistence.entity;

public enum TaskStatus {
    ON_TIME, LATE
}

//esta lista enum en MySQL muestra los elementos por ubicacion .
//no salen los nombres sino la posicion en el array.
//en este caso para ON_TIME sale 0 y para LATE sale 1
//en swagger se muestran los nombres.
