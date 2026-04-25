CREATE TABLE transactions (
    id UUID PRIMARY KEY,
    reference_no VARCHAR(50) UNIQUE NOT NULL,
    type VARCHAR(20) NOT NULL, -- TRANSFER, TOPUP, etc
    status VARCHAR(20) NOT NULL, -- PENDING, SUCCESS, FAILED

    source_account_id UUID,
    destination_account_id UUID,

    amount NUMERIC(18,2) NOT NULL,
    description TEXT,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_tx_source
        FOREIGN KEY (source_account_id)
        REFERENCES accounts(id),

    CONSTRAINT fk_tx_destination
        FOREIGN KEY (destination_account_id)
        REFERENCES accounts(id)
);