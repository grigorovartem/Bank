package DAO;

import Entity.Transfer;

import java.util.List;

public interface TransferDAO {

    void mergeTransfer(Transfer transfer);

    List<Transfer> transfersByUser(Long userId);

    List<Transfer> getAll();
}
