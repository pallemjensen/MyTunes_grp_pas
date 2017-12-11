/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.gui;

 
 

import org.jaudiotagger.audio.AudioFile; 
import org.jaudiotagger.audio.AudioHeader; 
import org.jaudiotagger.audio.asf.io.MetadataReader;
import org.jaudiotagger.audio.exceptions.CannotWriteException; 
import org.jaudiotagger.audio.mp3.MP3File; 
import org.jaudiotagger.tag.FieldKey; 
import org.jaudiotagger.tag.Tag; 
import org.jaudiotagger.tag.id3.AbstractID3v2Tag; 
import org.jaudiotagger.tag.id3.ID3v24Tag; 
import sun.rmi.runtime.Log;
 

 
public class Mp3MetadataReader extends MetadataReader { 
 private final MP3File audioMP3; 
 Tag tag; 

 public Mp3MetadataReader(AudioFile file) { 
  audioMP3 = (MP3File) file; 
 } 
 
 public Tag getTag() { 
  if (audioMP3.hasID3v1Tag() || audioMP3.hasID3v2Tag()) { 
   tag = audioMP3.getTag(); 
  } else { 
   tag = new ID3v24Tag(); 
   audioMP3.setID3v2TagOnly((AbstractID3v2Tag) tag); 
   try { 
    audioMP3.commit(); 
   } catch (CannotWriteException ignore) { 
    //todo
   } 
  } 
  return tag; 
 } 
}