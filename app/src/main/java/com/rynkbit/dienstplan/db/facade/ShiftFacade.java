package com.rynkbit.dienstplan.db.facade;

import android.content.Context;

import com.rynkbit.dienstplan.db.repository.ShiftRepository;
import com.rynkbit.dienstplan.entities.Shift;

import java.util.List;

/**
 * Created by michael on 12/10/17.
 */

public class ShiftFacade implements Facade<Shift>{
    private final Context context;

    public ShiftFacade(Context context){
        this.context = context;
    }

    @Override
    public List<Shift> getAll() {
        return ShiftRepository.getInstance(context)
                .getAll();
    }

    @Override
    public Shift getById(long id) {
        return ShiftRepository.getInstance(context)
                .getById(id);
    }

    @Override
    public void merge(Shift object) {
        ShiftRepository.getInstance(context)
                .merge(object);
    }

    @Override
    public void remove(long id) {
        ShiftRepository.getInstance(context)
                .remove(id);
    }
}
