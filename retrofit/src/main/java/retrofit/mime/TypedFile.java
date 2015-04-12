/*
 * Copyright (C) 2010 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package retrofit.mime;

import java.io.File;
import java.io.IOException;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;

/**
 * File and its mime type.
 *
 * @author Bob Lee (bob@squareup.com)
 */
public class TypedFile implements TypedInput, TypedOutput {
  private static final int BUFFER_SIZE = 4096;

  private final String mimeType;
  private final File file;

  /**
   * Constructs a new typed file.
   *
   * @throws NullPointerException if file or mimeType is null
   */
  public TypedFile(String mimeType, File file) {
    if (mimeType == null) {
      throw new NullPointerException("mimeType");
    }
    if (file == null) {
      throw new NullPointerException("file");
    }
    this.mimeType = mimeType;
    this.file = file;
  }

  /** Returns the file. */
  public File file() {
    return file;
  }

  @Override public String mimeType() {
    return mimeType;
  }

  @Override public long length() {
    return file.length();
  }

  @Override public String fileName() {
    return file.getName();
  }

  @Override public BufferedSource in() throws IOException {
    return Okio.buffer(Okio.source(file));
  }

  @Override public void writeTo(BufferedSink sink) throws IOException {
    Source source = null;
    try {
      source = Okio.source(file);
      sink.writeAll(source);
    } finally {
      if (source != null) {
        source.close();
      }
    }
  }

  /**
   * Atomically moves the contents of this file to a new location.
   *
   * @param destination file
   * @throws java.io.IOException if the move fails
   */
  public void moveTo(TypedFile destination) throws IOException {
    if (!mimeType().equals(destination.mimeType())) {
      throw new IOException("Type mismatch.");
    }
    if (!file.renameTo(destination.file())) {
      throw new IOException("Rename failed!");
    }
  }

  @Override public String toString() {
    return file.getAbsolutePath() + " (" + mimeType() + ")";
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof TypedFile) {
      TypedFile rhs = (TypedFile) o;
      return file.equals(rhs.file);
    }
    return false;
  }

  @Override public int hashCode() {
    return file.hashCode();
  }
}
