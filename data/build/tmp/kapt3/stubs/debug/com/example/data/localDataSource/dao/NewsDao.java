package com.example.data.localDataSource.dao;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0005J\u001f\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\n"}, d2 = {"Lcom/example/data/localDataSource/dao/NewsDao;", "", "getAllNews", "", "Lcom/example/data/model/listModel/Data;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertNews", "", "list", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
@androidx.room.Dao
public abstract interface NewsDao {
    
    @androidx.room.Insert(onConflict = 1, entity = com.example.data.model.listModel.Data.class)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertNews(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.data.model.listModel.Data> list, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM Data")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getAllNews(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.data.model.listModel.Data>> $completion);
}