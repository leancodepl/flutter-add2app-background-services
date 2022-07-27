// Autogenerated from Pigeon (v2.0.3), do not edit directly.
// See also: https://pub.dev/packages/pigeon

package co.leancode.add2appbackgroundservice.pigeon;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MessageCodec;
import io.flutter.plugin.common.StandardMessageCodec;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/** Generated class from Pigeon. */
@SuppressWarnings({"unused", "unchecked", "CodeBlock2Expr", "RedundantSuppression"})
public class Api {

  /** Generated class from Pigeon that represents data sent in messages. */
  public static class ComputationNotification {
    private @NonNull String title;
    public @NonNull String getTitle() { return title; }
    public void setTitle(@NonNull String setterArg) {
      if (setterArg == null) {
        throw new IllegalStateException("Nonnull field \"title\" is null.");
      }
      this.title = setterArg;
    }

    private @NonNull String message;
    public @NonNull String getMessage() { return message; }
    public void setMessage(@NonNull String setterArg) {
      if (setterArg == null) {
        throw new IllegalStateException("Nonnull field \"message\" is null.");
      }
      this.message = setterArg;
    }

    private @NonNull Long percentProgress;
    public @NonNull Long getPercentProgress() { return percentProgress; }
    public void setPercentProgress(@NonNull Long setterArg) {
      if (setterArg == null) {
        throw new IllegalStateException("Nonnull field \"percentProgress\" is null.");
      }
      this.percentProgress = setterArg;
    }

    /** Constructor is private to enforce null safety; use Builder. */
    private ComputationNotification() {}
    public static final class Builder {
      private @Nullable String title;
      public @NonNull Builder setTitle(@NonNull String setterArg) {
        this.title = setterArg;
        return this;
      }
      private @Nullable String message;
      public @NonNull Builder setMessage(@NonNull String setterArg) {
        this.message = setterArg;
        return this;
      }
      private @Nullable Long percentProgress;
      public @NonNull Builder setPercentProgress(@NonNull Long setterArg) {
        this.percentProgress = setterArg;
        return this;
      }
      public @NonNull ComputationNotification build() {
        ComputationNotification pigeonReturn = new ComputationNotification();
        pigeonReturn.setTitle(title);
        pigeonReturn.setMessage(message);
        pigeonReturn.setPercentProgress(percentProgress);
        return pigeonReturn;
      }
    }
    @NonNull Map<String, Object> toMap() {
      Map<String, Object> toMapResult = new HashMap<>();
      toMapResult.put("title", title);
      toMapResult.put("message", message);
      toMapResult.put("percentProgress", percentProgress);
      return toMapResult;
    }
    static @NonNull ComputationNotification fromMap(@NonNull Map<String, Object> map) {
      ComputationNotification pigeonResult = new ComputationNotification();
      Object title = map.get("title");
      pigeonResult.setTitle((String)title);
      Object message = map.get("message");
      pigeonResult.setMessage((String)message);
      Object percentProgress = map.get("percentProgress");
      pigeonResult.setPercentProgress((percentProgress == null) ? null : ((percentProgress instanceof Integer) ? (Integer)percentProgress : (Long)percentProgress));
      return pigeonResult;
    }
  }
  private static class FlutterMainApiCodec extends StandardMessageCodec {
    public static final FlutterMainApiCodec INSTANCE = new FlutterMainApiCodec();
    private FlutterMainApiCodec() {}
  }

  /** Generated class from Pigeon that represents Flutter messages that can be called from Java.*/
  public static class FlutterMainApi {
    private final BinaryMessenger binaryMessenger;
    public FlutterMainApi(BinaryMessenger argBinaryMessenger){
      this.binaryMessenger = argBinaryMessenger;
    }
    public interface Reply<T> {
      void reply(T reply);
    }
    static MessageCodec<Object> getCodec() {
      return FlutterMainApiCodec.INSTANCE;
    }

  }
  private static class NativeMainApiCodec extends StandardMessageCodec {
    public static final NativeMainApiCodec INSTANCE = new NativeMainApiCodec();
    private NativeMainApiCodec() {}
    @Override
    protected Object readValueOfType(byte type, ByteBuffer buffer) {
      switch (type) {
        case (byte)128:         
          return ComputationNotification.fromMap((Map<String, Object>) readValue(buffer));
        
        default:        
          return super.readValueOfType(type, buffer);
        
      }
    }
    @Override
    protected void writeValue(ByteArrayOutputStream stream, Object value)     {
      if (value instanceof ComputationNotification) {
        stream.write(128);
        writeValue(stream, ((ComputationNotification) value).toMap());
      } else 
{
        super.writeValue(stream, value);
      }
    }
  }

  /** Generated interface from Pigeon that represents a handler of messages from Flutter.*/
  public interface NativeMainApi {
    void startService(@NonNull ComputationNotification notification);
    void stopService();

    /** The codec used by NativeMainApi. */
    static MessageCodec<Object> getCodec() {
      return NativeMainApiCodec.INSTANCE;
    }

    /** Sets up an instance of `NativeMainApi` to handle messages through the `binaryMessenger`. */
    static void setup(BinaryMessenger binaryMessenger, NativeMainApi api) {
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.NativeMainApi.startService", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              ArrayList<Object> args = (ArrayList<Object>)message;
              ComputationNotification notificationArg = (ComputationNotification)args.get(0);
              if (notificationArg == null) {
                throw new NullPointerException("notificationArg unexpectedly null.");
              }
              api.startService(notificationArg);
              wrapped.put("result", null);
            }
            catch (Error | RuntimeException exception) {
              wrapped.put("error", wrapError(exception));
            }
            reply.reply(wrapped);
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.NativeMainApi.stopService", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              api.stopService();
              wrapped.put("result", null);
            }
            catch (Error | RuntimeException exception) {
              wrapped.put("error", wrapError(exception));
            }
            reply.reply(wrapped);
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
    }
  }
  private static class NativeDialogApiCodec extends StandardMessageCodec {
    public static final NativeDialogApiCodec INSTANCE = new NativeDialogApiCodec();
    private NativeDialogApiCodec() {}
  }

  /** Generated interface from Pigeon that represents a handler of messages from Flutter.*/
  public interface NativeDialogApi {
    void closeDialog();

    /** The codec used by NativeDialogApi. */
    static MessageCodec<Object> getCodec() {
      return NativeDialogApiCodec.INSTANCE;
    }

    /** Sets up an instance of `NativeDialogApi` to handle messages through the `binaryMessenger`. */
    static void setup(BinaryMessenger binaryMessenger, NativeDialogApi api) {
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.NativeDialogApi.closeDialog", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              api.closeDialog();
              wrapped.put("result", null);
            }
            catch (Error | RuntimeException exception) {
              wrapped.put("error", wrapError(exception));
            }
            reply.reply(wrapped);
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
    }
  }
  private static class NativeBackgroundServiceApiCodec extends StandardMessageCodec {
    public static final NativeBackgroundServiceApiCodec INSTANCE = new NativeBackgroundServiceApiCodec();
    private NativeBackgroundServiceApiCodec() {}
    @Override
    protected Object readValueOfType(byte type, ByteBuffer buffer) {
      switch (type) {
        case (byte)128:         
          return ComputationNotification.fromMap((Map<String, Object>) readValue(buffer));
        
        default:        
          return super.readValueOfType(type, buffer);
        
      }
    }
    @Override
    protected void writeValue(ByteArrayOutputStream stream, Object value)     {
      if (value instanceof ComputationNotification) {
        stream.write(128);
        writeValue(stream, ((ComputationNotification) value).toMap());
      } else 
{
        super.writeValue(stream, value);
      }
    }
  }

  /** Generated interface from Pigeon that represents a handler of messages from Flutter.*/
  public interface NativeBackgroundServiceApi {
    void stopService();
    void openDialog();
    void updateNotification(@NonNull ComputationNotification notification);

    /** The codec used by NativeBackgroundServiceApi. */
    static MessageCodec<Object> getCodec() {
      return NativeBackgroundServiceApiCodec.INSTANCE;
    }

    /** Sets up an instance of `NativeBackgroundServiceApi` to handle messages through the `binaryMessenger`. */
    static void setup(BinaryMessenger binaryMessenger, NativeBackgroundServiceApi api) {
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.NativeBackgroundServiceApi.stopService", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              api.stopService();
              wrapped.put("result", null);
            }
            catch (Error | RuntimeException exception) {
              wrapped.put("error", wrapError(exception));
            }
            reply.reply(wrapped);
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.NativeBackgroundServiceApi.openDialog", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              api.openDialog();
              wrapped.put("result", null);
            }
            catch (Error | RuntimeException exception) {
              wrapped.put("error", wrapError(exception));
            }
            reply.reply(wrapped);
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.NativeBackgroundServiceApi.updateNotification", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              ArrayList<Object> args = (ArrayList<Object>)message;
              ComputationNotification notificationArg = (ComputationNotification)args.get(0);
              if (notificationArg == null) {
                throw new NullPointerException("notificationArg unexpectedly null.");
              }
              api.updateNotification(notificationArg);
              wrapped.put("result", null);
            }
            catch (Error | RuntimeException exception) {
              wrapped.put("error", wrapError(exception));
            }
            reply.reply(wrapped);
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
    }
  }
  private static Map<String, Object> wrapError(Throwable exception) {
    Map<String, Object> errorMap = new HashMap<>();
    errorMap.put("message", exception.toString());
    errorMap.put("code", exception.getClass().getSimpleName());
    errorMap.put("details", "Cause: " + exception.getCause() + ", Stacktrace: " + Log.getStackTraceString(exception));
    return errorMap;
  }
}
