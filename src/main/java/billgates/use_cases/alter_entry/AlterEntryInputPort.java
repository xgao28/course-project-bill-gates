package billgates.use_cases.alter_entry;

public interface AlterEntryInputPort {
    void alterEntry(int entryID, Object newValue, String alterColumn);
}