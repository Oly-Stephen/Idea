package com.stephen.thought.serviceImpl;

import com.stephen.thought.dto.ThoughtDto;
import com.stephen.thought.repository.ThoughtRepository;
import com.stephen.thought.service.TodoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;

class ThoughtServiceImplTest {

    // Which service we want to test
    @InjectMocks
    private ThoughtServiceImpl thoughtServiceImpl;

    // declare the dependencies
    @Mock
    private TodoService todoService;
    @Mock
    private ThoughtRepository thoughtRepository;
    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_create_a_student() {
        // Given
        ThoughtDto dto = new ThoughtDto(
                1,
                "Example Title",
                "The is an exaample title"
        );
}