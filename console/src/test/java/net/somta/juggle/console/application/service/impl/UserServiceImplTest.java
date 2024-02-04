package net.somta.juggle.console.application.service.impl;

import net.somta.juggle.console.domain.user.UserAO;
import net.somta.juggle.console.domain.user.repository.IUserRepository;
import net.somta.juggle.console.infrastructure.repository.UserRepositoryImpl;
import org.apache.ibatis.ognl.ASTSequence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

    private UserServiceImpl userService;
    @Mock
    private IUserRepository userRepository;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void updateUser() {
        UserAO userAo = new UserAO();
        userAo.setUserName("juggle");
        userAo.setPassword("juggle");
        Mockito.doNothing().when(userRepository).updateUser(userAo);
        Boolean updateFlag = userService.updateUser(userAo);
        Assertions.assertEquals(true,updateFlag);
    }

    @Test
    void queryUserById() {
        Long userId = 1L;
        UserAO expectedUser = new UserAO();
        expectedUser.setId(userId);
        expectedUser.setUserName("juggle");
        expectedUser.setPassword("juggle");

        when(userRepository.getUserById(userId)).thenReturn(expectedUser);

        UserAO result = userService.queryUserById(userId);
        assertEquals(userId, result.getId());
        assertEquals("juggle", result.getPassword());
    }

    @Test
    void queryUserByUserName() {

    }
}