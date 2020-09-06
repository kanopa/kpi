package com.example.tablewarelab3

class ModelTableware {
    public var spinnerText: String? = "";
    public var Manufactory1: String? = "";
    public var Manufactory2: String? = "";
    public var Manufactory3: String? = "";
    public var Price1: String? = "";
    public var Price2: String? = "";
    public var Price3: String? = "";

    constructor(spinnerText: String?,
                Manufactory1: String?,
                Manufactory2: String?,
                Manufactory3: String?,
                Price1: String?,
                Price2: String?,
                Price3: String?) {
        this.spinnerText = spinnerText
        this.Manufactory1 = Manufactory1
        this.Manufactory2 = Manufactory2
        this.Manufactory3 = Manufactory3
        this.Price1 = Price1
        this.Price2 = Price2
        this.Price3 = Price3
    }
}