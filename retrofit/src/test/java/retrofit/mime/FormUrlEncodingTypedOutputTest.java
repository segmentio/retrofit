// Copyright 2013 Square, Inc.
package retrofit.mime;

import okio.Buffer;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FormUrlEncodingTypedOutputTest {
  @Test public void urlEncoding() throws Exception {
    FormUrlEncodedTypedOutput fe = new FormUrlEncodedTypedOutput();
    fe.addField("a&b", "c=d");
    fe.addField("space, the", "final frontier");

    Buffer buffer = new Buffer();
    fe.writeTo(buffer);
    String actual = buffer.readByteString().utf8();
    assertThat(actual).isEqualTo("a%26b=c%3Dd&space%2C+the=final+frontier");
  }

  @Test public void utf8encoding() throws Exception {
    FormUrlEncodedTypedOutput fe = new FormUrlEncodedTypedOutput();
    fe.addField("ooɟ", "ɹɐq");

    Buffer buffer = new Buffer();
    fe.writeTo(buffer);
    String actual = buffer.readByteString().utf8();
    assertThat(actual).isEqualTo("oo%C9%9F=%C9%B9%C9%90q");
  }

  @Test public void encodedPairs() throws Exception {
    FormUrlEncodedTypedOutput fe = new FormUrlEncodedTypedOutput();
    fe.addField("sim", "ple");

    Buffer buffer1 = new Buffer();
    fe.writeTo(buffer1);
    String actual1 = buffer1.readByteString().utf8();
    assertThat(actual1).isEqualTo("sim=ple");

    fe.addField("hey", "there");
    fe.addField("help", "me");

    Buffer buffer2 = new Buffer();
    fe.writeTo(buffer2);
    String actual2 = buffer2.readByteString().utf8();
    assertThat(actual2).isEqualTo("sim=ple&hey=there&help=me");
  }
}
