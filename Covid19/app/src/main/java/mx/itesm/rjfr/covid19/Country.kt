package mx.itesm.rjfr.covid19

class Country (val name: String, val cases: Int):Comparable<Country>{
    override fun compareTo(other: Country): Int {
        return name.compareTo(other.name)
    }

    //static
    companion object{
        var countryArr = arrayOf(
            Country("Mexico", 1024),
            Country("España", 4096)
        )
    }

}