package com.example.data.localDataSource.room;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&\u00a8\u0006\u0006"}, d2 = {"Lcom/example/data/localDataSource/room/NewsDataBase;", "Landroidx/room/RoomDatabase;", "()V", "getDao", "Lcom/example/data/localDataSource/dao/NewsDao;", "Companion", "data_debug"})
@androidx.room.Database(version = 1, entities = {com.example.data.model.listModel.Data.class})
public abstract class NewsDataBase extends androidx.room.RoomDatabase {
    @org.jetbrains.annotations.Nullable
    private static com.example.data.localDataSource.room.NewsDataBase dataBase;
    @org.jetbrains.annotations.NotNull
    public static final com.example.data.localDataSource.room.NewsDataBase.Companion Companion = null;
    
    public NewsDataBase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.data.localDataSource.dao.NewsDao getDao();
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bR\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\f"}, d2 = {"Lcom/example/data/localDataSource/room/NewsDataBase$Companion;", "", "()V", "dataBase", "Lcom/example/data/localDataSource/room/NewsDataBase;", "getDataBase", "()Lcom/example/data/localDataSource/room/NewsDataBase;", "setDataBase", "(Lcom/example/data/localDataSource/room/NewsDataBase;)V", "getInstance", "context", "Landroid/app/Application;", "data_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.Nullable
        public final com.example.data.localDataSource.room.NewsDataBase getDataBase() {
            return null;
        }
        
        public final void setDataBase(@org.jetbrains.annotations.Nullable
        com.example.data.localDataSource.room.NewsDataBase p0) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.data.localDataSource.room.NewsDataBase getInstance(@org.jetbrains.annotations.NotNull
        android.app.Application context) {
            return null;
        }
    }
}