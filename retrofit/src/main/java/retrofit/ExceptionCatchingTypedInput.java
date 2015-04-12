package retrofit;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Sink;
import okio.Timeout;
import retrofit.mime.TypedInput;

class ExceptionCatchingTypedInput implements TypedInput {
  private final TypedInput delegate;
  private final ExceptionCatchingSource delegateSource;

  ExceptionCatchingTypedInput(TypedInput delegate) throws IOException {
    this.delegate = delegate;
    this.delegateSource = new ExceptionCatchingSource(delegate.in());
  }

  @Override public String mimeType() {
    return delegate.mimeType();
  }

  @Override public long length() {
    return delegate.length();
  }

  @Override public BufferedSource in() throws IOException {
    return delegateSource;
  }

  IOException getThrownException() {
    return delegateSource.thrownException;
  }

  boolean threwException() {
    return delegateSource.thrownException != null;
  }

  private static class ExceptionCatchingSource implements BufferedSource {
    private final BufferedSource delegate;
    private IOException thrownException;

    ExceptionCatchingSource(BufferedSource delegate) {
      this.delegate = delegate;
    }

    @Override public Buffer buffer() {
      return delegate.buffer();
    }

    @Override public boolean exhausted() throws IOException {
      try {
        return delegate.exhausted();
      } catch (IOException e) {
        thrownException = e;
        throw e;
      }
    }

    @Override public void require(long byteCount) throws IOException {
      try {
        delegate.require(byteCount);
      } catch (IOException e) {
        thrownException = e;
        throw e;
      }
    }

    @Override public boolean request(long byteCount) throws IOException {
      try {
        return delegate.request(byteCount);
      } catch (IOException e) {
        thrownException = e;
        throw e;
      }
    }

    @Override public byte readByte() throws IOException {
      try {
        return delegate.readByte();
      } catch (IOException e) {
        thrownException = e;
        throw e;
      }
    }

    @Override public short readShort() throws IOException {
      try {
        return delegate.readShort();
      } catch (IOException e) {
        thrownException = e;
        throw e;
      }
    }

    @Override public short readShortLe() throws IOException {
      try {
        return delegate.readShortLe();
      } catch (IOException e) {
        thrownException = e;
        throw e;
      }
    }

    @Override public int readInt() throws IOException {
      try {
        return delegate.readInt();
      } catch (IOException e) {
        thrownException = e;
        throw e;
      }
    }

    @Override public int readIntLe() throws IOException {
      try {
        return delegate.readIntLe();
      } catch (IOException e) {
        thrownException = e;
        throw e;
      }
    }

    @Override public long readLong() throws IOException {
      try {
        return delegate.readLong();
      } catch (IOException e) {
        thrownException = e;
        throw e;
      }
    }

    @Override public long readLongLe() throws IOException {
      try {
        return delegate.readLongLe();
      } catch (IOException e) {
        thrownException = e;
        throw e;
      }
    }

    @Override public void skip(long byteCount) throws IOException {
      try {
        delegate.skip(byteCount);
      } catch (IOException e) {
        thrownException = e;
        throw e;
      }
    }

    @Override public ByteString readByteString() throws IOException {
      try {
        return delegate.readByteString();
      } catch (IOException e) {
        thrownException = e;
        throw e;
      }
    }

    @Override public ByteString readByteString(long byteCount) throws IOException {
      try {
        return delegate.readByteString(byteCount);
      } catch (IOException e) {
        thrownException = e;
        throw e;
      }
    }

    @Override public byte[] readByteArray() throws IOException {
      try {
        return delegate.readByteArray();
      } catch (IOException e) {
        thrownException = e;
        throw e;
      }
    }

    @Override public byte[] readByteArray(long byteCount) throws IOException {
      try {
        return delegate.readByteArray(byteCount);
      } catch (IOException e) {
        thrownException = e;
        throw e;
      }
    }

    @Override public int read(byte[] sink) throws IOException {
      try {
        return delegate.read(sink);
      } catch (IOException e) {
        thrownException = e;
        throw e;
      }
    }

    @Override public void readFully(byte[] sink) throws IOException {
      try {
        delegate.readFully(sink);
      } catch (IOException e) {
        thrownException = e;
        throw e;
      }
    }

    @Override public int read(byte[] sink, int offset, int byteCount) throws IOException {
      try {
        return delegate.read(sink, offset, byteCount);
      } catch (IOException e) {
        thrownException = e;
        throw e;
      }
    }

    @Override public void readFully(Buffer sink, long byteCount) throws IOException {
      try {
        delegate.readFully(sink, byteCount);
      } catch (IOException e) {
        thrownException = e;
        throw e;
      }
    }

    @Override public long readAll(Sink sink) throws IOException {
      try {
        return delegate.readAll(sink);
      } catch (IOException e) {
        thrownException = e;
        throw e;
      }
    }

    @Override public String readUtf8() throws IOException {
      try {
        return delegate.readUtf8();
      } catch (IOException e) {
        thrownException = e;
        throw e;
      }
    }

    @Override public String readUtf8(long byteCount) throws IOException {
      try {
        return delegate.readUtf8(byteCount);
      } catch (IOException e) {
        thrownException = e;
        throw e;
      }
    }

    @Override public String readUtf8Line() throws IOException {
      try {
        return delegate.readUtf8Line();
      } catch (IOException e) {
        thrownException = e;
        throw e;
      }
    }

    @Override public String readUtf8LineStrict() throws IOException {
      try {
        return delegate.readUtf8LineStrict();
      } catch (IOException e) {
        thrownException = e;
        throw e;
      }
    }

    @Override public String readString(Charset charset) throws IOException {
      try {
        return delegate.readString(charset);
      } catch (IOException e) {
        thrownException = e;
        throw e;
      }
    }

    @Override public String readString(long byteCount, Charset charset) throws IOException {
      try {
        return delegate.readString(byteCount, charset);
      } catch (IOException e) {
        thrownException = e;
        throw e;
      }
    }

    @Override public long indexOf(byte b) throws IOException {
      try {
        return delegate.indexOf(b);
      } catch (IOException e) {
        thrownException = e;
        throw e;
      }
    }

    @Override public long indexOf(byte b, long fromIndex) throws IOException {
      try {
        return delegate.indexOf(b, fromIndex);
      } catch (IOException e) {
        thrownException = e;
        throw e;
      }
    }

    @Override public long indexOfElement(ByteString targetBytes) throws IOException {
      try {
        return delegate.indexOfElement(targetBytes);
      } catch (IOException e) {
        thrownException = e;
        throw e;
      }
    }

    @Override public long indexOfElement(ByteString targetBytes, long fromIndex)
        throws IOException {
      try {
        return delegate.indexOfElement(targetBytes, fromIndex);
      } catch (IOException e) {
        thrownException = e;
        throw e;
      }
    }

    @Override public InputStream inputStream() {
      return delegate.inputStream();
    }

    @Override public long read(Buffer sink, long byteCount) throws IOException {
      try {
        return delegate.read(sink, byteCount);
      } catch (IOException e) {
        thrownException = e;
        throw e;
      }
    }

    @Override public Timeout timeout() {
      return delegate.timeout();
    }

    @Override public void close() throws IOException {
      try {
        delegate.close();
      } catch (IOException e) {
        thrownException = e;
        throw e;
      }
    }
  }
}
