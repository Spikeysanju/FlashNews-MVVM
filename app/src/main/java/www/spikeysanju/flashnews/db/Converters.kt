package www.spikeysanju.flashnews.db

import androidx.room.TypeConverter
import javax.xml.transform.Source

class Converters {

    @TypeConverter
    fun fromSource(source: www.spikeysanju.flashnews.model.Source):String {

        return source.name
    }


    @TypeConverter
    fun toSource(name:String):www.spikeysanju.flashnews.model.Source {

        return  www.spikeysanju.flashnews.model.Source(name,name)
    }

}