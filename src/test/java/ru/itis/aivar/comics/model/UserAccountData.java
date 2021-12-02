package ru.itis.aivar.comics.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAccountData implements Cloneable{
    private String email;
    private String password;
    private String username;

    @Override
    public UserAccountData clone() {
        try {
            UserAccountData clone = (UserAccountData) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
