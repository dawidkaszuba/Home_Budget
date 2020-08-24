package pl.dawidkaszuba.homebudget.model;

import java.util.List;

import io.reactivex.Single;
import pl.dawidkaszuba.homebudget.pojo.PlannedCashFlow;

public interface ModelPlannedCashFlowContact {
    Single<List<PlannedCashFlow>> getPlannedCashFlowFromHttp();
}
