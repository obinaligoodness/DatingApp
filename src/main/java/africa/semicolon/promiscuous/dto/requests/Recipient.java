package africa.semicolon.promiscuous.dto.requests;

import lombok.*;


@Setter
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class Recipient {
    private String name;
    @NonNull
    private String email;
}
