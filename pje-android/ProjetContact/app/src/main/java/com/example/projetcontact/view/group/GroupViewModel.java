package com.example.projetcontact.view.group;

import android.app.Application;
import com.example.projetcontact.objet.Groups;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class GroupViewModel extends AndroidViewModel {
    private GroupRepository mRepository;

    private LiveData<List<Groups>> mAllGroups;

    public GroupViewModel(Application application){
        super(application);
        mRepository = new GroupRepository(application);
        mAllGroups = mRepository.getAllGroups();
    }

    public LiveData<List<Groups>> getmAllGroups(){ return mAllGroups;}

    public void insert(Groups groups){ mRepository.insert(groups);}
    public void delete(Groups groups) { mRepository.delete(groups);}

}
