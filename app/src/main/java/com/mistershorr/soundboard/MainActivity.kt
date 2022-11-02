package com.mistershorr.soundboard

import android.media.AudioManager
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mistershorr.soundboard.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.HashMap

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    lateinit var soundPool : SoundPool
    var aNoteE = 0
    var bNoteE = 0
    var bbNoteE = 0
    var cNote = 0
    var csNote = 0
    var dNote = 0
    var dsNote = 0
    var eNote = 0
    var fNote = 0
    var fsNote = 0
    var gNote = 0
    var gsNote = 0
    var aNote = 0
    var bbNote = 0
    var bNote = 0
    var cNoteE = 0
    var csNoteE = 0
    var dNoteE = 0
    var dsNoteE = 0
    var eNoteE = 0
    var fNoteE = 0
    var fsNoteE = 0
    var gNoteE = 0
    var gsNoteE = 0
    var lgNoteE = 0

    var test = "A 500 A 500 B 1000 A 500 LG 1000"
    lateinit var songList: List<String>

    lateinit var noteList: List<Note>

    // instance variable for the view binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // define the binding variable
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val inputStream = resources.openRawResource(R.raw.song)
        val jsonString = inputStream.bufferedReader().use {
            // the last line of the use function is returned
            it.readText()
        }

        val gson = Gson()
        val sType = object : TypeToken<List<Note>>() {}.type
        noteList = gson.fromJson<List<Note>>(jsonString, sType)

        Log.d(TAG, "list: $noteList")

        initializeSoundPool()
        setListeners()
    }

    private fun setListeners() {
        val soundBoardListener = SoundBoardListener()
        binding.buttonMainC.setOnClickListener(soundBoardListener)
        binding.buttonMainCs.setOnClickListener(soundBoardListener)
        binding.buttonMainD.setOnClickListener(soundBoardListener)
        binding.buttonMainDs.setOnClickListener(soundBoardListener)
        binding.buttonMainE.setOnClickListener(soundBoardListener)
        binding.buttonMainF.setOnClickListener(soundBoardListener)
        binding.buttonMainFs.setOnClickListener(soundBoardListener)
        binding.buttonMainG.setOnClickListener(soundBoardListener)
        binding.buttonMainGs.setOnClickListener(soundBoardListener)
        binding.buttonMainA.setOnClickListener(soundBoardListener)
        binding.buttonMainBb.setOnClickListener(soundBoardListener)
        binding.buttonMainB.setOnClickListener(soundBoardListener)

        binding.buttonMainPlaySong.setOnClickListener {
            songList = test.split(" ")
            playSong(noteList)
        }

    }

    private fun initializeSoundPool() {

        this.volumeControlStream = AudioManager.STREAM_MUSIC
        soundPool = SoundPool(10, AudioManager.STREAM_MUSIC, 0)
        soundPool.setOnLoadCompleteListener(SoundPool.OnLoadCompleteListener { soundPool, sampleId, status ->
           // isSoundPoolLoaded = true
        })
        aNoteE = soundPool.load(this, R.raw.scalea, 1)
        bNoteE = soundPool.load(this, R.raw.scaleb, 1)
        bbNoteE = soundPool.load(this, R.raw.scalebb, 1)
        cNote =  soundPool.load(this, R.raw.scalec, 1)
        csNote =  soundPool.load(this, R.raw.scalecs, 1)
        dNote =  soundPool.load(this, R.raw.scaled, 1)
        dsNote =  soundPool.load(this, R.raw.scaleds, 1)
        eNote =  soundPool.load(this, R.raw.scalee, 1)
        fNote =  soundPool.load(this, R.raw.scalef, 1)
        fsNote =  soundPool.load(this, R.raw.scalefs, 1)
        gNote =  soundPool.load(this, R.raw.scaleg, 1)
        gsNote =  soundPool.load(this, R.raw.scalegs, 1)
        aNote = soundPool.load(this, R.raw.scalehigha, 1)
        bbNote = soundPool.load(this, R.raw.scalehighbb, 1)
        bNote = soundPool.load(this, R.raw.scalehighb, 1)
        cNoteE = soundPool.load(this, R.raw.scalehighc, 1)
        csNoteE = soundPool.load(this, R.raw.scalehighcs, 1)
        dNoteE = soundPool.load(this, R.raw.scalehighd, 1)
        dsNoteE = soundPool.load(this, R.raw.scalehighds, 1)
        eNoteE = soundPool.load(this, R.raw.scalehighe, 1)
        fNoteE = soundPool.load(this, R.raw.scalehighf, 1)
        fsNoteE = soundPool.load(this, R.raw.scalehighfs, 1)
        gNoteE = soundPool.load(this, R.raw.scalehighg, 1)
        gsNoteE = soundPool.load(this, R.raw.scalehighgs, 1)
        lgNoteE = soundPool.load(this, R.raw.scalelowg, 1)
    }

    private fun playNote(noteId : Int) {
        soundPool.play(noteId, 1f, 1f, 1, 0, 1f)
    }

    private fun playSong(song: List<Note>) {
        // loop though list of notes
            // play the note
            // delay for the delay
        for(item in song) {
            when(item.note) {
                "A" -> playNote(aNoteE)
                "B" -> playNote(bNoteE)
                "Bb" -> playNote(bbNoteE)
                "C" -> playNote(cNote)
                "Cs" -> playNote(csNote)
                "D" -> playNote(dNote)
                "Ds" -> playNote(dsNote)
                "E" -> playNote(eNote)
                "F" -> playNote(fNote)
                "Fs" -> playNote(fsNote)
                "G" -> playNote(gNote)
                "Gs" -> playNote(gsNote)
                "HA" -> playNote(aNote)
                "HB" -> playNote(bNote)
                "HBb" -> playNote(bbNote)
                "HC" -> playNote(cNoteE)
                "HCs" -> playNote(csNoteE)
                "HD" -> playNote(dNoteE)
                "HDs" -> playNote(dsNoteE)
                "HE" -> playNote(eNoteE)
                "HF" -> playNote(fNoteE)
                "HFs" -> playNote(fsNoteE)
                "HG" -> playNote(gNoteE)
                "HGs" -> playNote(gsNoteE)
                "LG" -> playNote(lgNoteE)
            }
        }
    }

    private fun delay(time: Long) {
        try {
            Thread.sleep(time)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    private inner class SoundBoardListener : View.OnClickListener {
        override fun onClick(v: View?) {
            when(v?.id) {
                R.id.button_main_c -> playNote(cNote)
                R.id.button_main_cs -> playNote(csNote)
                R.id.button_main_d -> playNote(dNote)
                R.id.button_main_ds -> playNote(dsNote)
                R.id.button_main_e -> playNote(eNote)
                R.id.button_main_f -> playNote(fNote)
                R.id.button_main_fs -> playNote(fsNote)
                R.id.button_main_g -> playNote(gNote)
                R.id.button_main_gs -> playNote(gsNote)
                R.id.button_main_a -> playNote(aNote)
                R.id.button_main_bb -> playNote(bbNote)
                R.id.button_main_b -> playNote(bNote)
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return when (keyCode) {
            KeyEvent.KEYCODE_A -> {
                playNote(cNote)
                true
            }
            KeyEvent.KEYCODE_W -> {
                playNote(csNote)
                true
            }
            KeyEvent.KEYCODE_S -> {
                playNote(dNote)
                true
            }
            KeyEvent.KEYCODE_E -> {
                playNote(dsNote)
                true
            }
            KeyEvent.KEYCODE_D-> {
                playNote(eNote)
                true
            }
            KeyEvent.KEYCODE_F -> {
                playNote(fNote)
                true
            }
            KeyEvent.KEYCODE_T-> {
                playNote(fsNote)
                true
            }
            KeyEvent.KEYCODE_G -> {
                playNote(gNote)
                true
            }
            KeyEvent.KEYCODE_Y -> {
                playNote(gsNote)
                true
            }
            KeyEvent.KEYCODE_H -> {
                playNote(aNote)
                true
            }
            KeyEvent.KEYCODE_U -> {
                playNote(bbNote)
                true
            }
            KeyEvent.KEYCODE_J -> {
                playNote(bNote)
                true
            }
            else -> super.onKeyUp(keyCode, event)
        }
    }
}