package com.example.data.localDataSource.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.data.model.listModel.Data;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class NewsDao_Impl implements NewsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Data> __insertionAdapterOfData;

  public NewsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfData = new EntityInsertionAdapter<Data>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Data` (`avatar`,`email`,`first_name`,`id`,`last_name`) VALUES (?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Data value) {
        if (value.getAvatar() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getAvatar());
        }
        if (value.getEmail() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getEmail());
        }
        if (value.getFirst_name() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getFirst_name());
        }
        stmt.bindLong(4, value.getId());
        if (value.getLast_name() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getLast_name());
        }
      }
    };
  }

  @Override
  public Object insertNews(final List<Data> list, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfData.insert(list);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getAllNews(final Continuation<? super List<Data>> $completion) {
    final String _sql = "SELECT * FROM Data";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Data>>() {
      @Override
      public List<Data> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfAvatar = CursorUtil.getColumnIndexOrThrow(_cursor, "avatar");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfFirstName = CursorUtil.getColumnIndexOrThrow(_cursor, "first_name");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfLastName = CursorUtil.getColumnIndexOrThrow(_cursor, "last_name");
          final List<Data> _result = new ArrayList<Data>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Data _item;
            final String _tmpAvatar;
            if (_cursor.isNull(_cursorIndexOfAvatar)) {
              _tmpAvatar = null;
            } else {
              _tmpAvatar = _cursor.getString(_cursorIndexOfAvatar);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpFirst_name;
            if (_cursor.isNull(_cursorIndexOfFirstName)) {
              _tmpFirst_name = null;
            } else {
              _tmpFirst_name = _cursor.getString(_cursorIndexOfFirstName);
            }
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpLast_name;
            if (_cursor.isNull(_cursorIndexOfLastName)) {
              _tmpLast_name = null;
            } else {
              _tmpLast_name = _cursor.getString(_cursorIndexOfLastName);
            }
            _item = new Data(_tmpAvatar,_tmpEmail,_tmpFirst_name,_tmpId,_tmpLast_name);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
